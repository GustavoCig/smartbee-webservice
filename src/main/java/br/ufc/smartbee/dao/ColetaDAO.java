package br.ufc.smartbee.dao;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.google.gson.Gson;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.util.JPAUtil;

public class ColetaDAO {

	EntityManager em = JPAUtil.createEntityManager();

	@SuppressWarnings("unchecked")
	public String getLastCollects(String idColmeia, int tempohora) {
		try {
			Query query = em.createNativeQuery("SELECT * FROM Coleta where idColmeia='" + idColmeia + "' and datahora "
					+ "between (SELECT subdate(CURRENT_TIMESTAMP(), INTERVAL " + tempohora + " HOUR)) and now()",
					Coleta.class);
			ArrayList<Coleta> coleta = (ArrayList<Coleta>) query.getResultList();
			return new Gson().toJson(coleta);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public String getLastCollectsMinuts(String idColmeia, int tempominutos) {
		String tempo = ""+tempominutos;
		try {
			if (tempominutos > 0 && tempominutos < 10) {
				tempo = "0"+tempominutos;
			} else if (tempominutos <= 0 || tempominutos > 59) {
				return "Sem coletas neste intervalo de tempo";
			}
			Query query = em.createNativeQuery(
					"SELECT * FROM Coleta where idColmeia='" + idColmeia + "' and datahora "
							+ "between (SELECT subtime(CURRENT_TIMESTAMP(), \'00:" + tempo + ":00\')) and now()",
					Coleta.class);
			ArrayList<Coleta> coleta = (ArrayList<Coleta>) query.getResultList();
			return new Gson().toJson(coleta);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		} finally {
			em.close();
		}
	}

}
