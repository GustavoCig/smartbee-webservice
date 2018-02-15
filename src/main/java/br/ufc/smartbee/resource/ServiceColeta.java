package br.ufc.smartbee.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.ColetaDAO;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeia;
import br.ufc.smartbee.modelo.Colmeia_coleta;
import br.ufc.smartbee.modelo.Sensor;

@Path("coleta")
public class ServiceColeta {

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TodosApiarios(String jsonrecebido) {
		try {
			Coleta coleta = new Gson().fromJson(jsonrecebido, Coleta.class);
			new ColetaDAO().insereColeta(coleta.getColetas());
			return Response.ok(null).build();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return Response.serverError().build();
		}
	}

	@GET
	@Path("{idcolmeia}/{temperatura}/{umidade}/{tensaosensores}/{tensaorepetidores}/{tempexterna}/{umidexterna}/{bateriagsm}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPorURL(@PathParam("idcolmeia") long idColmeia, @PathParam("temperatura") String temperatura,
			@PathParam("umidade") String umidade, @PathParam("tensaosensores") String tensaosensores,
			@PathParam("tensaorepetidores") String tensaorepetidores,
			@PathParam("tensaorepetidores") String tempexterna, @PathParam("tensaorepetidores") String umidexterna,
			@PathParam("tensaorepetidores") String bateriagsm) {
	
		
			List<Colmeia_coleta> lista = new ArrayList<>();
			Colmeia colmeia = new Colmeia();
			colmeia.setId(idColmeia);
			Sensor sensor1 = new Sensor();
			Sensor sensor2 = new Sensor();
			Sensor sensor3 = new Sensor();
			Sensor sensor4 = new Sensor();
			Sensor sensor5 = new Sensor();
			Sensor sensor6 = new Sensor();
			Sensor sensor7 = new Sensor();

			// TEMPERATURA
			Colmeia_coleta temp = new Colmeia_coleta();
			temp.setColmeia_id(colmeia);
			sensor1.setId(1);
			temp.setSensor_id(sensor1);
			temp.setValor(temperatura);
			lista.add(temp);

			// UMIDADE
			Colmeia_coleta umid = new Colmeia_coleta();
			umid.setColmeia_id(colmeia);
			sensor2.setId(2);
			umid.setSensor_id(sensor2);
			umid.setValor(umidade);
			lista.add(umid);

			// TENSAO SENSORES
			Colmeia_coleta sens = new Colmeia_coleta();
			sens.setColmeia_id(colmeia);
			sensor3.setId(3);
			sens.setSensor_id(sensor3);
			sens.setValor(tensaosensores);
			lista.add(sens);

			// TENSAO REPETIDORES
			Colmeia_coleta rept = new Colmeia_coleta();
			rept.setColmeia_id(colmeia);
			sensor4.setId(4);
			rept.setSensor_id(sensor4);
			rept.setValor(tensaorepetidores);
			lista.add(rept);

			// TEMPERATURA EXTERNA
			Colmeia_coleta temperaturaexterna = new Colmeia_coleta();
			temperaturaexterna.setColmeia_id(colmeia);
			sensor5.setId(5);
			temperaturaexterna.setSensor_id(sensor5);
			temperaturaexterna.setValor(tempexterna);
			lista.add(temperaturaexterna);

			// UMIDADE EXTERNA
			Colmeia_coleta umidadeexterna = new Colmeia_coleta();
			umidadeexterna.setColmeia_id(colmeia);
			sensor6.setId(6);
			umidadeexterna.setSensor_id(sensor6);
			umidadeexterna.setValor(umidexterna);
			lista.add(umidadeexterna);

			// TENSAO BATERIA GSM
			Colmeia_coleta gsm = new Colmeia_coleta();
			gsm.setColmeia_id(colmeia);
			sensor7.setId(7);
			gsm.setSensor_id(sensor7);
			gsm.setValor(bateriagsm);
			lista.add(gsm);

			Coleta coleta = new Coleta();
			coleta.setColetas(lista);
			return new ColetaDAO().insereColeta(coleta.getColetas());
		
	}
}
