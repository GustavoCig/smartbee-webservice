package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufc.smartbee.modelo.AtualMediaMinimoMaximo;
import br.ufc.smartbee.modelo.Colmeia;
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
					"SELECT c from Colmeia c where amostra = (SELECT max(amostra) from Colmeia where entityId=:idColmeia)",
					Colmeia.class);
			query.setParameter("idColmeia", idColmeia);
			return new GsonConverter().converteColmeias(query.getSingleResult());
		} finally {
			em.close();
		}
	}

	public String getAvgMinMaxAtual(String idColmeia, String medida) {
		try {
			AtualMediaMinimoMaximo ammm = new AtualMediaMinimoMaximo();
			// PEGA MEDIA
			String media = "select avg(" + medida + ") from Colmeia where EntityId='" + idColmeia + "'";
			Query query = em.createQuery(media);
			ammm.setMedia((Double) query.getSingleResult());

			// PEGA MAXIMO
			String maximo = "select max(" + medida + ") from Colmeia where EntityId='" + idColmeia + "'";
			query = em.createQuery(maximo);
			ammm.setMaximo((Double) query.getSingleResult());

			// PEGA MINIMO
			String minimo = "select min(" + medida + ") from Colmeia where EntityId='" + idColmeia + "'";
			query = em.createQuery(minimo);
			ammm.setMinimo((Double) query.getSingleResult());

			// PEGA ATUAL
			String atual = "select (" + medida
					+ ") from Colmeia where amostra=(SELECT max(amostra) from Colmeia where EntityId='" + idColmeia
					+ "')";
			query = em.createQuery(atual);
			ammm.setAtual((Double) query.getSingleResult());

			return new GsonConverter().converteColmeias(ammm);
		} finally {
			em.close();
		}
	}

}
