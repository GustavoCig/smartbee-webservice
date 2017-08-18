package br.ufc.smartbee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ufc.smartbee.dao.GenericDAO;
import br.ufc.smartbee.modelo.Apiario;

@Path("apiarios")
public class ServiceApiarios {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String TodosApiarios() {	
		return new GenericDAO().getListaEntidade(Apiario.class);
	}


}
