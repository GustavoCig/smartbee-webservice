package br.ufc.smartbee.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;

import br.ufc.smartbee.modelo.Sensor;
import br.ufc.smartbee.util.JPAUtil;

@Table(name = "sensor")
public class SensorDAO {
	EntityManager em = JPAUtil.createEntityManager();

	public void insertNewSensor(Sensor newsensor) {
		try {
			em.getTransaction().begin();
			em.persist(newsensor);
			em.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			em.close();
		}
	}
	
	public List<Sensor> getAllSensors() {
		String sql = "SELECT s FROM Sensor s";
		Query query = em.createQuery(sql, Sensor.class);
		List<Sensor> sensors = query.getResultList();
		return sensors;
	}

}
