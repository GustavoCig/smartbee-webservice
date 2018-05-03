package br.ufc.smartbee.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sensor")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String tipo;
	@NotNull
	private String descricao;
	@NotNull
	private String modelo;
	@NotNull
	private String marca;
	@ManyToOne
	@JoinColumn(name="login_cadastro", referencedColumnName="id")
	private Users login_cadastro;
	@ManyToOne
	@JoinColumn(name="login_alteracao", referencedColumnName="id")
	private Users login_alteracao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
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
