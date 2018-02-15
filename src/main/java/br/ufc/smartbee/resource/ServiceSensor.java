package br.ufc.smartbee.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import br.ufc.smartbee.dao.SensorDAO;
import br.ufc.smartbee.modelo.Sensor;
import br.ufc.smartbee.seguranca.Seguro;

@Path("sensor")
public class ServiceSensor {

	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public Response listSensors() {
		List<Sensor> sensors = new SensorDAO().getAllSensors();
		if(sensors.size() == 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(new Gson().toJson(sensors)).build();
	}
	
	@POST
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	public Response addSensor(String json) {
		try {
			SensorDAO create = new SensorDAO();
			Sensor newsensor = new Gson().fromJson(json, Sensor.class);
			if(!isNull(newsensor))
				return Response.status(Status.NOT_ACCEPTABLE).build();
			create.insertNewSensor(newsensor);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	public boolean isNull(Sensor sensor) {
		if (sensor.getDescricao().isEmpty() || sensor.getMarca().isEmpty() || sensor.getModelo().isEmpty()
				|| sensor.getTipo().isEmpty())
			return false;
		return true;
	}

}