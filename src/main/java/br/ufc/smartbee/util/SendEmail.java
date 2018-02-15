package br.ufc.smartbee.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {

	public static boolean welcomeEmail(String mensagem, String email_usuario) {

		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("teste@teste.com", "teste"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom(email_usuario);
			email.setSubject("Projeto Smartbee");
			email.setMsg(mensagem);
			email.addTo(email_usuario);
			email.send();
		} catch (EmailException e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		return true;
	}
}
