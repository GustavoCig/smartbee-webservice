package br.ufc.smartbee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufc.smartbee.modelo.Apiario;
import br.ufc.smartbee.util.JPAUtil;

public class ApiarioDAO {
	EntityManager em = JPAUtil.createEntityManager();
	
	public void insertApiario(Apiario apiario) {
		try {
			em.getTransaction().begin();
			em.persist(apiario);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	public List<Apiario> getApiarios() {
		TypedQuery<Apiario> query = em.createQuery("SELECT a from Apiario a", Apiario.class);
		try {
			List<Apiario> apiario = (List<Apiario>) query.getResultList();
			return apiario;
		} catch (Exception e) {
			return null;
		}

	}
}
