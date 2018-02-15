package br.ufc.smartbee.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.ColetaDAO;
import br.ufc.smartbee.dao.ColmeiaDAO;
import br.ufc.smartbee.modelo.AtualMediaMinimoMaximo;
import br.ufc.smartbee.modelo.Colmeia;
import br.ufc.smartbee.seguranca.Seguro;
import br.ufc.smartbee.util.Filter;

@Path("/colmeias")
public class ServiceColmeias {

	// LISTA TODAS AS COLMEIAS CADASTRADAS
	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public Response TodasColmeias() {
		List<Colmeia> colmeias = new ColmeiaDAO().getAll();
		if (colmeias == null || colmeias.size() == 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Filter filter = new Filter();
		colmeias = filter.filtraAtributosColmeia(colmeias);
		colmeias = filter.cleanUser(colmeias);
		return Response.ok(new Gson().toJson(colmeias)).build();
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
	@Path("{id}")
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public Response dadosColetadosPorColmeia(@PathParam("id") String idColmeia) {
		return new ColetaDAO().ultimasAmostras(idColmeia);
	}

	// LISTA DADOS DETALHADOS SOBRE OS VALORES MONITORADOS(MAX, MIN, AVG e ATUAL)
	@GET
	@Seguro
	@Path("{id}/{sensor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response infoColmeia(@PathParam("id") String idColmeia, @PathParam("sensor") String sensor_id) {
		AtualMediaMinimoMaximo ammm = new ColmeiaDAO().getAvgMinMaxAtual(idColmeia, sensor_id);
		if (ammm == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(new Gson().toJson(ammm)).build();
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Seguro
	@Path("{id}/hist/{sensor}/{hora}/hour")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaColetasHoras(@PathParam("id") String idColmeia, @PathParam("sensor") String idSensor,
			@PathParam("hora") String tempohora) {
		return new ColetaDAO().getLastCollects(idColmeia, idSensor, tempohora);
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Seguro
	@Path("{id}/hist/{sensor}/{minuto}/min")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaColetasMinutos(@PathParam("id") String idColmeia, @PathParam("sensor") String idsensor,
			@PathParam("minuto") int tempominuto) {

		return new ColetaDAO().getLastCollectsMinuts(idColmeia, tempominuto, idsensor);
	}

	// PEGA ULTIMAS X COLETAS
	@GET
	@Seguro
	@Path("{id}/hist/{idsensor}/last/{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaUltimas(@PathParam("id") String idColmeia, @PathParam("idsensor") String idsensor,
			@PathParam("numero") String amostras) {

		return new ColmeiaDAO().getLast(idColmeia, idsensor, amostras);
	}

	@GET
	@Seguro
	@Path("/tensao/{id_sensor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pegaTensoes(@PathParam("id_sensor") String id_sensor) {
		return Response.ok(new Gson().toJson(new ColmeiaDAO().getTensao(id_sensor))).build();
	}
}