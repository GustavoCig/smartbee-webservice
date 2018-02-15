package br.ufc.smartbee.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufc.smartbee.modelo.Users;
import br.ufc.smartbee.util.Criptografia;
import br.ufc.smartbee.util.JPAUtil;

public class SignupDAO {
	EntityManager em = JPAUtil.createEntityManager();

	public void insertNewUser(Users newuser) {
		try {
			newuser.setPassword(Criptografia.toCript(newuser.getPassword()));
			em.getTransaction().begin();
			newuser.setCreated_at(Calendar.getInstance());
			newuser.setUpdated_at(Calendar.getInstance());
			em.persist(newuser);
			em.getTransaction().commit();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public Users getUserByEmail(String email) {
		Users user = null;
		try {
//		TypedQuery<Users> query = em.createQuery("SELECT u from Users u where email = :email", Users.class);
		Query query = em.createNativeQuery("select * from users where email= '"+email+"'", Users.class);
		//query.setParameter("email", email);
		user = (Users) query.getSingleResult();
		return user;
		}catch (Exception e) {
			return user = null;
		}
	}

	public Users getUserByToken(String token) {
		Query query = em.createQuery("SELECT u from Users u where remember_token = :remember_token", Users.class);
		query.setParameter("remember_token", token);
		try {
			Users user = (Users) query.getSingleResult();
			user = changeLoginTime(user);
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	public void addTokenToUser(int idUser, String token) {
		Users users = em.find(Users.class, idUser);
		em.getTransaction().begin();
		users.setRemember_token(token);
		em.getTransaction().commit();
	}

	public void updateUser(Users updateuser) {
		Users users = em.find(Users.class, updateuser.getId());
		em.getTransaction().begin();
		users = setFields(users, updateuser);
		users = updateuser;
		users.setUpdated_at(Calendar.getInstance());
		em.merge(users);
		em.getTransaction().commit();
	}

	public Users setFields(Users old, Users update) {
		Users tosave = new Users();
		tosave = update;
		if (!(update.getName() == null) || !(update.getName().isEmpty())) {
			tosave.setName(old.getName());
		}
		if (!(update.getEmail() == null) || !(update.getEmail().isEmpty())) {
			tosave.setEmail(old.getEmail());
		}
		if (!(update.getAddress() == null) || !(update.getAddress().isEmpty())) {
			tosave.setAddress(old.getAddress());
		}
		if (!(update.getPhone() == null) || !(update.getPhone().isEmpty())) {
			tosave.setPhone(old.getPhone());
		}
		if (!(update.getCellphone() == null) || !(update.getCellphone().isEmpty())) {
			tosave.setCellphone(old.getCellphone());
		}
		if (!(update.getAddress() == null) || !(update.getAddress().isEmpty())) {
			
		}
		tosave.setConfirmed(old.getConfirmed());
		tosave.setAdmin(old.getAdmin());
		tosave.setLast_login(old.getLast_login());
		tosave.setRemember_token(old.getRemember_token());
		tosave.setCreated_at(old.getCreated_at());
		tosave.setUpdated_at(old.getUpdated_at());
		return tosave;
	}

	public Users changeLoginTime(Users recebido) {
		Users users = em.find(Users.class, recebido.getId());
		em.getTransaction().begin();
		users.setLast_login(Calendar.getInstance());
		em.merge(users);
		em.getTransaction().commit();
		return users;
	}
}