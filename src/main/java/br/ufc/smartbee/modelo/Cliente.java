package br.ufc.smartbee.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity

@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Cliente {
	@Id
	private Integer idApiario;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String endereco;
	@NotNull
	private String telefone;
	@NotNull
	private String celular;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Calendar created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updated_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar deleted_at;
	@OneToOne
	private Users login_cadastro;
	@OneToOne
	private Users login_alteracao;
	
	public Integer getIdApiario() {
		return idApiario;
	}
	public void setIdApiario(Integer idApiario) {
		this.idApiario = idApiario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Calendar getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Calendar created_at) {
		this.created_at = created_at;
	}
	public Calendar getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Calendar updated_at) {
		this.updated_at = updated_at;
	}
	public Calendar getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(Calendar deleted_at) {
		this.deleted_at = deleted_at;
	}
	public Users getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(Users login_cadastro) {
		this.login_cadastro = login_cadastro;
	}
	public Users getLogin_alteracao() {
		return login_alteracao;
	}
	public void setLogin_alteracao(Users login_alteracao) {
		this.login_alteracao = login_alteracao;
	}
	
	
}
