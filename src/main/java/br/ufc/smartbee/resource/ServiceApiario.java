package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.ApiarioDAO;
import br.ufc.smartbee.dao.GenericDAO;
import br.ufc.smartbee.modelo.Apiario;
import br.ufc.smartbee.seguranca.Seguro;

@Path("apiario")
public class ServiceApiario {

	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public Response TodosApiarios() {
		try {
		return Response.ok(new Gson().toJson(new ApiarioDAO().getApiarios())).build();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("add")
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TodosApiarios(String jsonrecebido) {
		try {
			Apiario apiario = new Gson().fromJson(jsonrecebido, Apiario.class);
			new ApiarioDAO().insertApiario(apiario);
			System.out.println(apiario.getLogin_alteracao().getId());
			System.out.println(apiario.getLogin_cadastro());
			return Response.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
		}
	}
}