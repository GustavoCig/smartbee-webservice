package br.ufc.smartbee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.ufc.smartbee.dao.ColmeiaDAO;
import br.ufc.smartbee.dao.GenericDAO;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;

@Path("/colmeias")
public class ServiceColmeias {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String TodasColmeias() {
		return new GenericDAO().getListaEntidade(Colmeias_Cadastradas.class);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String colmeiaPorID(@PathParam("id") String idColmeia) {
		System.out.println(idColmeia);
		return new ColmeiaDAO().exibeColmeia(idColmeia);
	}

}