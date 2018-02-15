package br.ufc.smartbee.modelo;

import java.util.Calendar;

public class UltimaVoltagem {
	private String voltagemRepetidor;
	private Calendar datahora;

	public String getVoltagem() {
		return voltagemRepetidor;
	}

	public void setVoltagem(String voltagem) {
		this.voltagemRepetidor = voltagem;
	}

	public Calendar getDatahora() {
		return datahora;
	}

	public void setDatahora(Calendar datahora) {
		this.datahora = datahora;
	}
}