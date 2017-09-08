package br.ufc.smartbee.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

@Entity
public class Credencial {
	@Id
	private String login;
	private String nome;
	
	
	private String token;
	@NotNull
	private String senha;
	
	private String ativado;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAtivado() {
		return ativado;
	}

	public void setAtivado(String ativado) {
		this.ativado = ativado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@PrePersist
	void preInsert() {
	   ativado = "N";
	}
}