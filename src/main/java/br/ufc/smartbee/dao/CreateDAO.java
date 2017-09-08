package br.ufc.smartbee.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import br.ufc.smartbee.modelo.Coleta;
import br.ufc.smartbee.modelo.Colmeias_Cadastradas;
import br.ufc.smartbee.modelo.Credencial;
import br.ufc.smartbee.util.Criptografia;
import br.ufc.smartbee.util.JPAUtil;

public class CreateDAO {
	EntityManager em = JPAUtil.createEntityManager();

	public void insereColeta(Coleta coleta) {
		try {
			atualiza(coleta.getIdColmeia());
			em.getTransaction().begin();
			em.persist(coleta);
			em.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			em.close();
		}
	}

	public void atualiza(String idColmeia) {
		Colmeias_Cadastradas colmeia = em.find(Colmeias_Cadastradas.class, idColmeia);
		em.getTransaction().begin();
		colmeia.setNumerocoletas(colmeia.getNumerocoletas() + 1);
		em.getTransaction().commit();
	}

	public void insereCadastro(Credencial credencial) {
		try {
			credencial.setSenha(Criptografia.toCript(credencial.getSenha()));

			em.getTransaction().begin();
			em.persist(credencial);
			em.getTransaction().commit();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public boolean verificaEmail(String login) {
		if (em.find(Credencial.class, login) == null) {
			return true;
		} else {
			return false;
		}

	}
}
