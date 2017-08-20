package br.ufc.smartbee.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Colmeia {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amostra;
	private String recvTime;
	private String fiwareServicePath;
	private String entityId;
	private String entityType;
	private String data;
	private String data_md;
	private String hora;
	private String hora_md;
	private Double som;
	private String som_md;
	private Double temperatura;
	private String temperatura_md;
	private Double umidade;
	private String umidade_md;
	private Double dioxido;
	private String dioxido_md;
	private Double tensaocolmeia;
	private String tensaocolmeia_md;
	private Double tensaorepetidor;
	private String tensaorepetidor_md;

	public Long getAmostra() {
		return amostra;
	}

	public void setAmostra(Long amostra) {
		this.amostra = amostra;
	}

	public String getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(String recvTime) {
		this.recvTime = recvTime;
	}

	public String getFiwareServicePath() {
		return fiwareServicePath;
	}

	public void setFiwareServicePath(String fiwareServicePath) {
		this.fiwareServicePath = fiwareServicePath;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData_md() {
		return data_md;
	}

	public void setData_md(String data_md) {
		this.data_md = data_md;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora_md() {
		return hora_md;
	}

	public void setHora_md(String hora_md) {
		this.hora_md = hora_md;
	}

	public Double getSom() {
		return som;
	}

	public void setSom(Double som) {
		this.som = som;
	}

	public String getSom_md() {
		return som_md;
	}

	public void setSom_md(String som_md) {
		this.som_md = som_md;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getTemperatura_md() {
		return temperatura_md;
	}

	public void setTemperatura_md(String temperatura_md) {
		this.temperatura_md = temperatura_md;
	}

	public Double getUmidade() {
		return umidade;
	}

	public void setUmidade(Double umidade) {
		this.umidade = umidade;
	}

	public String getUmidade_md() {
		return umidade_md;
	}

	public void setUmidade_md(String umidade_md) {
		this.umidade_md = umidade_md;
	}

	public Double getDioxido() {
		return dioxido;
	}

	public void setDioxido(Double dioxido) {
		this.dioxido = dioxido;
	}

	public String getDioxido_md() {
		return dioxido_md;
	}

	public void setDioxido_md(String dioxido_md) {
		this.dioxido_md = dioxido_md;
	}

	public Double getTensaocolmeia() {
		return tensaocolmeia;
	}

	public void setTensaocolmeia(Double tensaocolmeia) {
		this.tensaocolmeia = tensaocolmeia;
	}

	public String getTensaocolmeia_md() {
		return tensaocolmeia_md;
	}

	public void setTensaocolmeia_md(String tensaocolmeia_md) {
		this.tensaocolmeia_md = tensaocolmeia_md;
	}

	public Double getTensaorepetidor() {
		return tensaorepetidor;
	}

	public void setTensaorepetidor(Double tensaorepetidor) {
		this.tensaorepetidor = tensaorepetidor;
	}

	public String getTensaorepetidor_md() {
		return tensaorepetidor_md;
	}

	public void setTensaorepetidor_md(String tensaorepetidor_md) {
		this.tensaorepetidor_md = tensaorepetidor_md;
	}
}