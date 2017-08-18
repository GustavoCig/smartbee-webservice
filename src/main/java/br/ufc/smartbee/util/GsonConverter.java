package br.ufc.smartbee.util;

import java.util.List;

import com.google.gson.Gson;

import br.ufc.smartbee.modelo.Colmeias_Cadastradas;

public class GsonConverter {

	
	public String converteListadeObjetos(List<?> lista) {
		return new Gson().toJson(lista);
	}
	
	public String converteColmeia(Colmeias_Cadastradas cc) {
		return new Gson().toJson(cc);
	}

}
