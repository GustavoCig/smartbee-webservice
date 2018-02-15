package br.ufc.smartbee.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import br.ufc.smartbee.modelo.AtualMediaMinimoMaximo;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeia;
import br.ufc.smartbee.modelo.Colmeia_coleta;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.modelo.Sensor;
import br.ufc.smartbee.modelo.UltimaVoltagem;
import br.ufc.smartbee.util.Filter;
import br.ufc.smartbee.util.GsonConverter;
import br.ufc.smartbee.util.JPAUtil;

public class ColmeiaDAO {

	EntityManager em = JPAUtil.createEntityManager();

	// RETORNA UM JSON COM UM OBJETO COLMEIA SERIALIZADO
	public String exibeColmeia(String idColmeia) {
		try {
			return new GsonConverter().converteColmeias(em.find(Colmeias_Cadastradas.class, idColmeia));
		} finally {
			em.close();
		}
	}

	public List<Colmeia> getAll() {
		TypedQuery<Colmeia> query = em.createQuery("SELECT c from Colmeia c", Colmeia.class);
		try {
			List<Colmeia> colmeias = (List<Colmeia>) query.getResultList();
			return colmeias;
		} catch (Exception e) {
			return null;
		}
	}

	public String getLastCollectHivebee(String idColmeia) {
		try {
			Query query = em.createQuery(
					"SELECT c from Coleta c where amostra = (SELECT max(amostra) from coleta where idColmeia=:pidColmeia)",
					Coleta.class);
			query.setParameter("pidColmeia", idColmeia);
			Coleta coleta = (Coleta) query.getSingleResult();
			return new GsonConverter().converteColmeias(coleta);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		} finally {
			em.close();
		}
	}

	public AtualMediaMinimoMaximo getAvgMinMaxAtual(String idColmeia, String id_sensor) {
		try {
			int vazio = 0;
			AtualMediaMinimoMaximo ammm = new AtualMediaMinimoMaximo();
			Colmeia_coleta consulta = null;
			Query query;
			// PEGA MEDIA
			try {
				String media = "SELECT (CONVERT (AVG(CAST(valor AS DECIMAL(5,2))), CHAR(255))) as valor FROM colmeia_coleta c WHERE  c.sensor_id = "
						+ id_sensor + " and c.colmeia_id=" + idColmeia;
				query = em.createNativeQuery(media);

				DecimalFormat formato = new DecimalFormat("#.##");
				Double valor_media = Double.valueOf(query.getSingleResult().toString());

				ammm.setMedia(formato.format(valor_media).replace(",", "."));
			} catch (Exception e) {
				vazio++;
				ammm.setMedia(null);
			}
			// PEGA MAXIMO
			try {
				String maximo = "SELECT (CONVERT (MAX(CAST(valor AS DECIMAL(5,2))), CHAR(255))) as valor FROM colmeia_coleta WHERE  sensor_id = "
						+ id_sensor + " and colmeia_id=" + idColmeia;
				query = em.createNativeQuery(maximo);
				ammm.setMaximo(query.getSingleResult().toString());
			} catch (Exception e) {
				vazio++;
				ammm.setMaximo(null);
			}

			// PEGA MINIMO
			try {
				String minimo = "SELECT (CONVERT (MIN(CAST(valor AS DECIMAL(5,2))), CHAR(255))) as valor FROM colmeia_coleta WHERE  sensor_id = "
						+ id_sensor + " and colmeia_id=" + idColmeia;
				query = em.createNativeQuery(minimo);
				ammm.setMinimo(query.getSingleResult().toString());
			} catch (Exception e) {
				vazio++;
				ammm.setMinimo(null);
			}
			// PEGA ATUAL
			try {
				String sql = "SELECT * FROM colmeia_coleta c where c.sensor_id=" + id_sensor + " and c.colmeia_id="
						+ idColmeia + " ORDER BY c.id DESC";

				query = em.createNativeQuery(sql, Colmeia_coleta.class).setMaxResults(1);
				consulta = (Colmeia_coleta) query.getSingleResult();
				ammm.setAtual(consulta.getValor());
			} catch (Exception e) {
				vazio++;
				System.out.println(e.getLocalizedMessage());
				ammm.setAtual(null);
			}
			if (vazio == 4) {
				return ammm = null;
			}
			return ammm;
		} finally {
			em.close();
		}
	}

	public UltimaVoltagem getTensao(String id_sensor) {
		UltimaVoltagem ultimaVoltagem = new UltimaVoltagem();
		Colmeia colmeia = new Colmeia();
		colmeia.setId(3L);
		Sensor sensor = new Sensor();
		sensor.setId(Integer.valueOf(id_sensor));
		String sql = "";
		Query query;
		if (id_sensor.equals("3")) {
			sql = "SELECT * FROM colmeia_coleta where colmeia_id=2 and sensor_id="+id_sensor+" ORDER BY id DESC";
			query = em.createNativeQuery(sql, Colmeia_coleta.class).setMaxResults(1);
		} else {
			sql = "SELECT * FROM colmeia_coleta where sensor_id="+id_sensor+" ORDER BY id DESC";
			query = em.createNativeQuery(sql, Colmeia_coleta.class).setMaxResults(1);
		}
		
		Colmeia_coleta coleta = (Colmeia_coleta) query.getSingleResult();
		ultimaVoltagem.setDatahora(coleta.getCreated_at());
		ultimaVoltagem.setVoltagem(coleta.getValor());
		return ultimaVoltagem;
	}

	// DEVOLVE AS ULTIMAS COLETAS DEFINIDAS PELA QUANTIDADE
	public Response getLast(String idColmeia, String idsensor, String amostras) {
		if (Integer.valueOf(amostras) <= 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Colmeia colmeia = new Colmeia();
		colmeia.setId(Long.valueOf(idColmeia));
		Sensor sensor = new Sensor();
		sensor.setId(Integer.valueOf(idsensor));
		String sql = "SELECT c FROM Colmeia_coleta c where colmeia_id=:colmeiaid and sensor_id=:sensorid ORDER BY created_at DESC";
		Query query = em.createQuery(sql).setMaxResults(Integer.valueOf(amostras)).setParameter("colmeiaid", colmeia)
				.setParameter("sensorid", sensor);
		@SuppressWarnings("unchecked")
		List<Colmeia_coleta> coleta = query.getResultList();
		if (coleta == null || coleta.size() == 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Filter filter = new Filter();
		coleta = filter.filtraAtributosColmeiaColeta(coleta);
		Collections.reverse(coleta);
		return Response.ok(new Gson().toJson(coleta)).build();
	}

}
