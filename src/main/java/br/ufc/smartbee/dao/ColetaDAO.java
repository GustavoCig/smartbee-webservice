package br.ufc.smartbee.dao;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import br.ufc.smartbee.modelo.Colmeia_coleta;
import br.ufc.smartbee.util.Filter;
import br.ufc.smartbee.util.JPAUtil;

public class ColetaDAO {

	EntityManager em = JPAUtil.createEntityManager();

	@SuppressWarnings("unchecked")
	public Response getLastCollects(String idColmeia, String idsensor, String tempohora) {
		try {
			if (Integer.valueOf(tempohora) <= 0) {
				return Response.status(Status.BAD_GATEWAY).build();
			}
			Query query = em.createNativeQuery("SELECT * FROM colmeia_coleta where sensor_id=" + idsensor
					+ " and colmeia_id='" + idColmeia + "' and created_at	 "
					+ "between (SELECT subdate(CURRENT_TIMESTAMP(), INTERVAL " + tempohora + " HOUR)) and now()",
					Colmeia_coleta.class);
			List<Colmeia_coleta> coleta = query.getResultList();
			if (coleta == null || coleta.size() == 0) {
				return Response.status(Status.NOT_FOUND).build();
			}

			Filter filter = new Filter();
			coleta = filter.filtraAtributosColmeiaColeta(coleta);
			return Response.ok(new Gson().toJson(coleta)).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
		} finally {
			em.close();
		}
	}

	public Response ultimasAmostras(String idColmeia) {
		String sql = "select * from colmeia_coleta where colmeia_id = " + idColmeia
				+ " and created_at=(select max(created_at) from colmeia_coleta where colmeia_id="+idColmeia+")";
		Query query = em.createNativeQuery(sql, Colmeia_coleta.class);
		try {
			@SuppressWarnings("unchecked")
			List<Colmeia_coleta> coletas = query.getResultList();
			if (coletas == null || coletas.size() == 0) {
				return Response.status(Status.NOT_FOUND).build();
			}
			Filter filter = new Filter();
			coletas = filter.filtraAtributosColmeiaColeta(coletas);
			return Response.ok(new Gson().toJson(coletas)).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
		}
	}

	@SuppressWarnings("unchecked")
	public Response getLastCollectsMinuts(String idColmeia, int tempominutos, String idSensor) {
		String tempo = "" + tempominutos;
		List<Colmeia_coleta> coletas = null;
		try {
			if (tempominutos <= 0 || tempominutos > 59) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (tempominutos > 0 && tempominutos < 10) {
				tempo = "0" + tempominutos;
			}
			Query query = em.createNativeQuery("SELECT * FROM colmeia_coleta where sensor_id=" + idSensor
					+ " and colmeia_id='" + idColmeia + "' and created_at "
					+ "between (SELECT subtime(CURRENT_TIMESTAMP(), \'00:" + tempo + ":00\')) and now()",
					Colmeia_coleta.class);
			coletas = query.getResultList();

			if (coletas == null || coletas.size() == 0) {	
				return Response.status(Status.NOT_FOUND).build();
			}
			Filter filter = new Filter();
			filter.filtraAtributosColmeiaColeta(coletas);
			return Response.ok(new Gson().toJson(coletas)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		} finally {
			em.close();
		}
	}

	// add coleta
	public Response insereColeta(List<Colmeia_coleta> coleta) {
		Calendar tempo_entrada = Calendar.getInstance();
		try {
			em.getTransaction().begin();
			for (Colmeia_coleta colmeia_coleta : coleta) {
				colmeia_coleta.setCreated_at(tempo_entrada);
				em.persist(colmeia_coleta);
			}
			em.getTransaction().commit();
			return Response.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().build();
		} finally {
			em.close();
		}
	}

}
