package br.ufc.smartbee.dao;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import br.ufc.smartbee.modelo.AtualMediaMinimoMaximo;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.modelo.UltimaVoltagem;
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

	public String getLastCollectHivebee(String idColmeia) {
		try {
			Query query = em.createQuery(
					"SELECT c from Coleta c where amostra = (SELECT max(amostra) from Coleta where idColmeia=:pidColmeia)",
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

	public String getAvgMinMaxAtual(String idColmeia, String medida) {
		try {
			AtualMediaMinimoMaximo ammm = new AtualMediaMinimoMaximo();
			// PEGA MEDIA
			String media = "select avg(" + medida + ") from Coleta where idColmeia='" + idColmeia + "'";
			Query query = em.createQuery(media);
			DecimalFormat formato = new DecimalFormat("#.##");
			Double valor_media = Double.valueOf((Double) query.getSingleResult());
			ammm.setMedia(Double.valueOf(formato.format(valor_media).replace(",", ".")));

			// PEGA MAXIMO
			String maximo = "select max(" + medida + ") from Coleta where idColmeia='" + idColmeia + "'";
			query = em.createQuery(maximo);
			ammm.setMaximo((Double) query.getSingleResult());

			// PEGA MINIMO
			String minimo = "select min(" + medida + ") from Coleta where idColmeia='" + idColmeia + "'";
			query = em.createQuery(minimo);
			ammm.setMinimo((Double) query.getSingleResult());

			// PEGA ATUAL
			String atual = "select (" + medida
					+ ") from Coleta where amostra=(SELECT max(amostra) from Coleta where idColmeia='" + idColmeia
					+ "')";
			query = em.createQuery(atual);
			ammm.setAtual((Double) query.getSingleResult());

			return new GsonConverter().converteColmeias(ammm);
		} finally {
			em.close();
		}
	}

	public UltimaVoltagem getTensao() {
		UltimaVoltagem ultimaVoltagem = new UltimaVoltagem();
		String sql = "SELECT c FROM Coleta c ORDER BY amostra DESC";
		Query query = em.createQuery(sql).setMaxResults(1);
		Coleta coleta = (Coleta) query.getSingleResult();
		ultimaVoltagem.setDatahora(coleta.getDatahora());
		ultimaVoltagem.setVoltagem(coleta.getTensaorepetidor());
		return ultimaVoltagem;
	}
	
	public String getLast(String idColmeia, int numero) {
		String sql = "SELECT c FROM Coleta c where idColmeia=:pid ORDER BY amostra DESC";
		Query query = em.createQuery(sql).setMaxResults(numero).setParameter("pid", idColmeia);
		@SuppressWarnings("unchecked")
		List<Coleta> coleta = query.getResultList();
		return new Gson().toJson(coleta);
	}

}
