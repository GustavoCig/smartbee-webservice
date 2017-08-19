package br.ufc.smartbee.util;

import java.util.List;

import com.google.gson.Gson;

public class GsonConverter {

	public String converteListadeObjetos(List<?> lista) {
		return new Gson().toJson(lista);
	}

	public String converteColmeias(Object object) {
		return new Gson().toJson(object);
	}

}
