package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufc.smartbee.modelo.AtualMediaMinimoMaximo;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.util.GsonConverter;
import br.ufc.smartbee.util.JPAUtil;

public class ColmeiaDAO<T> {

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
//			ammm.setMedia((Double) query.getSingleResult());
			ammm.setMedia(query.getSingleResult());
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

}
