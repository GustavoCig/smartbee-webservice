package br.ufc.smartbee.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Colmeias_Cadastradas {
	@Id
	private String idColmeia;
	private Integer latitude;
	private String longitude;
	@NotNull
	private String datacriacao;
	private Integer numerocoletas;
	
	public String getIdColmeia() {
		return idColmeia;
	}
	public void setIdColmeia(String idColmeia) {
		this.idColmeia = idColmeia;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(String datacriacao) {
		this.datacriacao = datacriacao;
	}
	public Integer getNumerocoletas() {
		return numerocoletas;
	}
	public void setNumerocoletas(Integer numerocoletas) {
		this.numerocoletas = numerocoletas;
	}
	
	
}
