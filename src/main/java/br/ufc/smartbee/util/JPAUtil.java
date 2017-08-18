package br.ufc.smartbee.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("smartbee");

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
	
}
