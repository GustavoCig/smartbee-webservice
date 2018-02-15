package br.ufc.smartbee.resource;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DatatypeConverter;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.SignupDAO;
import br.ufc.smartbee.modelo.Users;
import br.ufc.smartbee.util.Criptografia;
import br.ufc.smartbee.util.JPAUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("login")
public class ServiceLogin {

	private static final String FRASE_SEGREDO = "smartbee";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fazerLogin(String credenciaisJson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		EntityManager em = JPAUtil.createEntityManager();
		try {

			Users credencial = new Gson().fromJson(credenciaisJson, Users.class);

			if (credencial.getRemember_token() == null || credencial.getRemember_token().isEmpty()) {
				// SE NAO FOR ENVIADO UM TOKEN NO JSON
				return firstAccess(credencial);
			} else {
				return loginByToken(credencial);
			}
		} finally {
			em.close();
		}

	}

	private Response firstAccess(Users user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Users consultaBanco = new SignupDAO().getUserByEmail(user.getEmail());
		String senhahash = Criptografia.toCript(user.getPassword());

		// SE NAO EXISTIR USUARIO
		if (consultaBanco == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		// ERRO NA SENHA INFORMADA
		else if (!senhahash.equals(consultaBanco.getPassword())) {
			return Response.status(Status.UNAUTHORIZED).build();	
			//Status.UNAUTHORIZED
		}
		// USUARIO AINDA NAO TEM PERMISSAO PARA ACESSO
		else if (consultaBanco.getConfirmed() == 0) {
			return Response.status(Status.FORBIDDEN).build();
		} else {
			String token = gerarToken(user.getEmail(), 365);
			consultaBanco.setRemember_token(token);
			new SignupDAO().addTokenToUser(consultaBanco.getId(), token);
			return Response.ok(new Gson().toJson(consultaBanco)).build();
		}
	}

	private Response loginByToken(Users user) {
		Users consultaBanco = new SignupDAO().getUserByToken(user.getRemember_token());
		if (!(consultaBanco == null)) {
			return Response.ok((new Gson().toJson(consultaBanco))).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	private String gerarToken(String login, Integer expiraEmDias) {

		// Defini qual vai ser o algoritmo da assinatura no caso vai ser o HMAC SHA512

		SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;

		// Data atual que data que o token foi gerado

		Date agora = new Date();

		// Define até que data o token é pelo quantidade de dias que foi passo pelo
		// parâmetro expiraEmDias

		Calendar expira = Calendar.getInstance();

		expira.add(Calendar.DAY_OF_MONTH, expiraEmDias);

		// Encoda a frase segredo pra base64 pra ser usada na geração do token

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FRASE_SEGREDO);

		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		// E finalmente utiliza o JWT builder pra gerar o token

		JwtBuilder construtor = Jwts.builder()

				.setIssuedAt(agora)// Data que o token foi gerado

				.setIssuer(login)// Coloca o login do usuário mais podia qualquer outra informação

				.signWith(algoritimoAssinatura, key)// coloca o algoritmo de assinatura e frase segredo já encodada

				.setExpiration(expira.getTime());// coloca até que data que o token é valido

		return construtor.compact();// Constrói o token retornando ele como uma String

	}

	public Claims validaToken(String token) {

		try {

			// JJWT vai validar o token caso o token não seja valido ele vai executar uma
			// exeption

			// o JJWT usa a frase segredo pra descodificar o token e ficando assim possivel

			// recuperar as informações que colocamos no payload

			Claims claims = Jwts.parser()

					.setSigningKey(DatatypeConverter.parseBase64Binary(FRASE_SEGREDO))

					.parseClaimsJws(token).getBody();

			// Aqui é um exemplo que se o token for valido e descodificado

			// vai imprimir o login que foi colocamos no token

			System.out.println(claims.getIssuer());

			return claims;

		} catch (Exception ex) {

			throw ex;

		}

	}
}
