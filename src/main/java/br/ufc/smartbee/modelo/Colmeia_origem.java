package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "colmeia_origem")
public class Colmeia_origem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Calendar created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updated_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar deleted_at;
	@ManyToOne
	@JoinColumn(name="login_cadastro", referencedColumnName="id")
	private Users login_cadastro;
	@ManyToOne
	@JoinColumn(name="login_alteracao", referencedColumnName="id")
	private Users login_alteracao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
