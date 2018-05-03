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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "apiario")
public class Apiario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	private String latitude;
	private String longitude;
	private String descricao;
	@ManyToOne
	@JoinColumn(name="login_cadastro", referencedColumnName="id")
	private Users login_cadastro;
	@ManyToOne
	@JoinColumn(name="login_alteracao", referencedColumnName="id")
	private Users login_alteracao;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Calendar created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updated_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar deleted_at;

	public Integer getIdApiario() {
		return id;
	}

	public void setIdApiario(Integer idApiario) {
		this.id = idApiario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

}
