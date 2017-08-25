package br.ufc.smartbee.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.ColetaDAO;
import br.ufc.smartbee.dao.ColmeiaDAO;
import br.ufc.smartbee.dao.GenericDAO;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.util.TiposSensoriados;

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

	// LISTA DADOS DETALHADOS SOBRE OS VALORES MONITORADOS
	@GET
	@Path("{id}/{medida}")
	@Produces(MediaType.APPLICATION_JSON)
	public String infoColmeia(@PathParam("id") String idColmeia, @PathParam("medida") TiposSensoriados medida) {
		return new ColmeiaDAO<>().getAvgMinMaxAtual(idColmeia, medida.toString());
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Path("{id}/historico/{tempo}/hour")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String pegaColetasHoras(@PathParam("id") String idColmeia, @PathParam("tempo") int tempohora) {
		return new Gson().toJson(new ColetaDAO().getLastCollects(idColmeia, tempohora)).replace("\\", "").replace("\"[", "[").replace("]\"", "]");
	}

	// PEGA COLETAS DE ACORDO COM O TEMPO PASSADO (EM HORAS)
	@GET
	@Path("{id}/historico/{minuto}/min")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String pegaColetasMinutos(@PathParam("id") String idColmeia, @PathParam("minuto") int tempominuto) {
		return new Gson().toJson(new ColetaDAO().getLastCollectsMinuts(idColmeia, tempominuto)).replace("\\", "").replace("\"[", "[").replace("]\"", "]");
	}
}