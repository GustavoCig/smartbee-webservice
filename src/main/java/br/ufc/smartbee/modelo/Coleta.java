package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Coleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amostra;
	private String idColmeia;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Calendar datahora;
	private Double som;
	private Double temperatura;
	private Double umidade;
	private Double dioxido;
	private Double tensaocolmeia;
	private Double tensaorepetidor;
	private Double tensaogateway;

	public Long getAmostra() {
		return amostra;
	}

	public void setAmostra(Long amostra) {
		this.amostra = amostra;
	}

	public String getIdColmeia() {
		return idColmeia;
	}

	public void setIdColmeia(String idColmeia) {
		this.idColmeia = idColmeia;
	}

	public Calendar getDatahora() {
		return datahora;
	}

	public void setDatahora(Calendar datahora) {
		this.datahora = datahora;
	}

	public Double getSom() {
		return som;
	}

	public void setSom(Double som) {
		this.som = som;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getUmidade() {
		return umidade;
	}

	public void setUmidade(Double umidade) {
		this.umidade = umidade;
	}

	public Double getDioxido() {
		return dioxido;
	}

	public void setDioxido(Double dioxido) {
		this.dioxido = dioxido;
	}

	public Double getTensaocolmeia() {
		return tensaocolmeia;
	}

	public void setTensaocolmeia(Double tensaocolmeia) {
		this.tensaocolmeia = tensaocolmeia;
	}

	public Double getTensaorepetidor() {
		return tensaorepetidor;
	}

	public void setTensaorepetidor(Double tensaorepetidor) {
		this.tensaorepetidor = tensaorepetidor;
	}

	public Double getTensaogateway() {
		return tensaogateway;
	}

	public void setTensaogateway(Double tensaogateway) {
		this.tensaogateway = tensaogateway;
	}
}
