package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.ufc.smartbee.util.GsonConverter;
import br.ufc.smartbee.util.JPAUtil;

public class GenericDAO<T> {
	EntityManager em = JPAUtil.createEntityManager();
	
	public String getListaEntidade(Class<?> classe) {		
		try {
			Session session = em.unwrap(Session.class);
			Criteria criteria = session.createCriteria(classe);
			return new GsonConverter().converteListadeObjetos(criteria.list());
		} finally {
			em.close();
		}
	}
}
