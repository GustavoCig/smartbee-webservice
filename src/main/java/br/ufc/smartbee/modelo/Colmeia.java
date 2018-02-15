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
@Table(name = "colmeia")
public class Colmeia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private Integer caixilho;
	private String latitude;
	private String longitude;
	@ManyToOne
	@JoinColumn(name="colmeia_tipo_id", referencedColumnName="id")
	private Colmeia_tipo colmeia_tipo;
	@ManyToOne
	@JoinColumn(name="colmeia_fundo_id", referencedColumnName="id")
	private Colmeia_fundo colmeia_fundo;
	@ManyToOne
	@JoinColumn(name="colmeia_origem_id", referencedColumnName="id")
	private Colmeia_origem colmeia_origem;
	
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
	public Integer getCaixilho() {
		return caixilho;
	}
	public void setCaixilho(Integer caixilho) {
		this.caixilho = caixilho;
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
	public Colmeia_tipo getColmeia_tipo_id() {
		return colmeia_tipo;
	}
	public void setColmeia_tipo_id(Colmeia_tipo colmeia_tipo_id) {
		this.colmeia_tipo = colmeia_tipo_id;
	}
	public Colmeia_fundo getColmeia_fundo_id() {
		return colmeia_fundo;
	}
	public void setColmeia_fundo_id(Colmeia_fundo colmeia_fundo_id) {
		this.colmeia_fundo = colmeia_fundo_id;
	}
	public Colmeia_origem getColmeia_origem_id() {
		return colmeia_origem;
	}
	public void setColmeia_origem_id(Colmeia_origem colmeia_origem_id) {
		this.colmeia_origem = colmeia_origem_id;
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