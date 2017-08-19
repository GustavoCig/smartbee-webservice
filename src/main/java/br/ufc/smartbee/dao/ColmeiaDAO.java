package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		Query query = em.createQuery("SELECT c from Colmeia c where amostra = (SELECT max(amostra) from Colmeia where entityId=:idColmeia)");
		query.setParameter("idColmeia", idColmeia);
		return new GsonConverter().converteColmeias(query.getSingleResult());
	}
	
	
}
