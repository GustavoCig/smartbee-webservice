package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;

import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.util.JPAUtil;

public class CreateDAO {
	EntityManager em = JPAUtil.createEntityManager();
	
	public void insereColeta(Coleta coleta) {
		try {
			em.getTransaction().begin();
			em.persist(coleta);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
}
