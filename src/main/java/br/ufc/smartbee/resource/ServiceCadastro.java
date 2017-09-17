package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import br.ufc.smartbee.dao.CreateDAO;
import br.ufc.smartbee.modelo.Credencial;

@Path("cadastro")
public class ServiceCadastro {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response realizaCadastro(String jsonrecebido) {
		try {
			CreateDAO create = new CreateDAO();
			Credencial novacredecial = new Gson().fromJson(jsonrecebido, Credencial.class);
			if(create.verificaEmail(novacredecial.getLogin())) {
				//NÃO EXISTE LOGIN COM NOME CORRESPONDENTE
				create.insereCadastro(novacredecial);
				System.out.println("Entrou aqui -  Ok");
				return Response.ok(novacredecial).build();
			}else {
				System.out.println("Entrou aqui -  Conflito");
				//LOGIN EXISTENTE
				return Response.status(Status.CONFLICT).build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
			
		}
	}

}
