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
@Table(name = "colmeia_coleta")
public class Colmeia_coleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@ManyToOne
	@JoinColumn(name="colmeia_id", referencedColumnName="id")
	private Colmeia colmeia_id;
	@ManyToOne
	@JoinColumn(name="sensor_id", referencedColumnName="id")
	private Sensor sensor_id;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Calendar created_at;
	private String valor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Colmeia getColmeia_id() {
		return colmeia_id;
	}
	public void setColmeia_id(Colmeia colmeia_id) {
		this.colmeia_id = colmeia_id;
	}
	public Sensor getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(Sensor sensor_id) {
		this.sensor_id = sensor_id;
	}
	public Calendar getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Calendar created_at) {
		this.created_at = created_at;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}