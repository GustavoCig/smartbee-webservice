package br.ufc.smartbee.modelo;

import java.util.Calendar;

public class UltimaVoltagem {
	private Double voltagemRepetidor;
	private Calendar datahora;

	public Double getVoltagem() {
		return voltagemRepetidor;
	}

	public void setVoltagem(Double voltagem) {
		this.voltagemRepetidor = voltagem;
	}

	public Calendar getDatahora() {
		return datahora;
	}

	public void setDatahora(Calendar datahora) {
		this.datahora = datahora;
	}
}