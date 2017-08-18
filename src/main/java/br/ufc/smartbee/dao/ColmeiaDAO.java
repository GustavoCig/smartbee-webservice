package br.ufc.smartbee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.util.GsonConverter;
import br.ufc.smartbee.util.JPAUtil;

public class ColmeiaDAO {

	EntityManager em = JPAUtil.createEntityManager();

	// RETORNA UM JSON COM UM OBJETO COLMEIA SERIALIZADO
	public String exibeColmeia(String idColmeia) {
		try {
			Query query = em.createQuery("select cc from Colmeias_Cadastradas cc where cc.idColmeia=:idcolmeia", Colmeias_Cadastradas.class);
			query.setParameter("idcolmeia", idColmeia);
			List<Colmeias_Cadastradas> cc = query.getResultList();
			return new GsonConverter().converteColmeia(cc.get(0));
		} finally {
			em.close();
		}
	}
}
