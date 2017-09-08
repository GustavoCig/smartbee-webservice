package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import br.ufc.smartbee.dao.ColetaDAO;
import br.ufc.smartbee.dao.ColmeiaDAO;
import br.ufc.smartbee.dao.GenericDAO;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.seguranca.Seguro;
import br.ufc.smartbee.util.TiposSensoriados;

@Path("/colmeias")
public class ServiceColmeias {

	// LISTA TODAS AS COLMEIAS CADASTRADAS
	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public String TodasColmeias() {
		return new GenericDAO<>().getListaEntidade(Colmeias_Cadastradas.class);
	}

	// LISTA AS INFORMACOES SOBRE A COLMEIA ESCOLHIDA
	@GET
	@Seguro
	@Path("{id}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public String infoColmeia(@PathParam("id") String idColmeia) {
		return new ColmeiaDAO().exibeColmeia(idColmeia);
	}

	// LISTA DADOS SOBRE A ULTIMA COLETA NA COLMEIA REPASSADA
	@GET
	@Seguro
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dadosColetadosPorColmeia(@PathParam("id") String idColmeia) {
		return Response.ok(new ColmeiaDAO().getLastCollectHivebee(idColmeia)).build();
	}

	// LISTA DADOS DETALHADOS SOBRE OS VALORES MONITORADOS
	@GET
	@Seguro
	@Path("{id}/{medida}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response infoColmeia(@PathParam("id") String idColmeia, @PathParam("medida") TiposSensoriados medida) {
		return Response.ok(new ColmeiaDAO().getAvgMinMaxAtual(idColmeia, medida.toString())).build();
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Seguro
	@Path("{id}/historico/{tempo}/hour")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaColetasHoras(@PathParam("id") String idColmeia, @PathParam("tempo") int tempohora) {
		return Response.ok(new ColetaDAO().getLastCollects(idColmeia, tempohora)).build();
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Seguro
	@Path("{id}/historico/{minuto}/min")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaColetasMinutos(@PathParam("id") String idColmeia, @PathParam("minuto") int tempominuto) {
		return Response.ok(new ColetaDAO().getLastCollectsMinuts(idColmeia, tempominuto)).build();
	}
	
	// PEGA ULTIMAS X COLETAS
		@GET
		@Seguro
		@Path("{id}/historico/ultimas/{numero}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response pegaUltimas(@PathParam("id") String idColmeia, @PathParam("numero") int numero) {
			
			return Response.ok(new ColmeiaDAO().getLast(idColmeia, numero)).build(); 
		}
	
	
	@GET
	@Seguro
	@Path("/tensao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaTensoes() {
		return Response.ok(new Gson().toJson(new ColmeiaDAO().getTensao())).build();
	}	
}