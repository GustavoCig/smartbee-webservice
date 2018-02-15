package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.SignupDAO;
import br.ufc.smartbee.modelo.Users;
import br.ufc.smartbee.util.SendEmail;

@Path("signup")
public class ServiceSignup {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newUser(String json) {
		try {
			
			SignupDAO signupDAO = new SignupDAO();
			System.out.println(json);
			Users newuser = new Gson().fromJson(json, Users.class);
			newuser.setId(null);
			signupDAO.insertNewUser(newuser);
			SendEmail.welcomeEmail("Bem vindo ao Smartbee", newuser.getEmail());
			return Response.ok(new Gson().toJson(newuser)).build();
		} catch (Exception e) {
			//TENTATIVA DE CADASTRAR UM USUARIO COM EMAIL JA CADASTRADO
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				return Response.status(Status.CONFLICT).build();
			}
			//ERRO NO JSON
			if (e.getCause() instanceof com.google.gson.stream.MalformedJsonException) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			//ERRO GERAL
			System.out.println("erro aqui: "+e);
			return Response.serverError().build();
		}
	}

	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(String json) {
		try {
			SignupDAO signupDAO = new SignupDAO();
			Users user = new Gson().fromJson(json, Users.class);
			signupDAO.updateUser(user);
			return Response.ok().build();

		} catch (Exception e) {
			//OCORRE QUANDO É ENVIADO UM JSON COM O ID VAZIO
			if (e.getCause() instanceof java.lang.NumberFormatException) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			//OCORRE QUANDO É ENVIADO UM JSON COM O ID DE VALOR 0
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			System.out.println(e.getMessage());
			return Response.serverError().build();
		}
	}

}