package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.CreateDAO;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.seguranca.Seguro;

@Path("add")
public class ServiceColeta {

	@POST
	@Seguro
	@Path("coleta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TodosApiarios(String jsonrecebido) {
		try {
			Coleta coleta = new Gson().fromJson(jsonrecebido, Coleta.class);
			new CreateDAO().insereColeta(coleta);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
