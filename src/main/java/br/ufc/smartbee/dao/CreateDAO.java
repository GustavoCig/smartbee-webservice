package br.ufc.smartbee.dao;

import javax.persistence.EntityManager;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.util.JPAUtil;

public class CreateDAO {
	EntityManager em = JPAUtil.createEntityManager();

	public void insereColeta(Coleta coleta) {
		try {
			atualiza(coleta.getIdColmeia());
			em.getTransaction().begin();
			em.persist(coleta);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	public void atualiza(String idColmeia) {
		Colmeias_Cadastradas colmeia = em.find(Colmeias_Cadastradas.class, idColmeia);
		  em.getTransaction().begin();
		  colmeia.setNumerocoletas(colmeia.getNumerocoletas()+1);
		  em.getTransaction().commit();
	}
}
