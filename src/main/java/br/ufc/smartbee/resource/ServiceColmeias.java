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

	// LISTA TODAS AS COLMEIAS CADASTRADAS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String TodasColmeias() {
		return new GenericDAO<>().getListaEntidade(Colmeias_Cadastradas.class);
	}

	// LISTA AS INFORMACOES SOBRE A COLMEIA ESCOLHIDA
	@GET
	@Path("{id}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public String infoColmeia(@PathParam("id") String idColmeia) {
		return new ColmeiaDAO<>().exibeColmeia(idColmeia);
	}

	// LISTA DADOS SOBRE A ULTIMA COLETA NA COLMEIA REPASSADA
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String dadosColetadosPorColmeia(@PathParam("id") String idColmeia) {
		return new ColmeiaDAO<>().getLastCollectHivebee(idColmeia);
	}
}