package br.ufc.smartbee.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import br.ufc.smartbee.modelo.Users;
import br.ufc.smartbee.util.Criptografia;
import br.ufc.smartbee.util.JPAUtil;

public class CreateDAO {
	EntityManager em = JPAUtil.createEntityManager();

	
	public void insereCadastro(Users user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		try {
			user.setPassword(Criptografia.toCript(user.getPassword()));
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public boolean verificaEmail(String login) {
		if (em.find(Users.class, login) == null) {
			return true;
		} else {
			return false;
		}
	}
}
