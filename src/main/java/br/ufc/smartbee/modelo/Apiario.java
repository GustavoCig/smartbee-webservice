package br.ufc.smartbee.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Apiario {
	@Id
	private Integer idApiario;
	@NotNull
	private String descricao;
	private String latitude;
	private String longitude;
	
	public Integer getIdApiario() {
		return idApiario;
	}
	public void setIdApiario(Integer idApiario) {
		this.idApiario = idApiario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
}
