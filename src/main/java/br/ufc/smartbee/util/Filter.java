package br.ufc.smartbee.util;

import java.util.List;
import br.ufc.smartbee.modelo.Colmeia;
import br.ufc.smartbee.modelo.Colmeia_coleta;
import br.ufc.smartbee.modelo.Users;

public class Filter {
	List<Colmeia> lista;

	// -------------------------------COLMEIAS E
	// TIPOS-----------------------------------------
	public List<Colmeia> filtraAtributosColmeia(List<Colmeia> lista) {
		this.lista = lista;
		cleanColmeiaFundo();
		cleanColmeiaOrigem();
		cleanColmeiaTipo();
		return this.lista;
	}

	public List<Colmeia_coleta> filtraAtributosColmeiaColeta(List<Colmeia_coleta> lista_coleta) {

		for (int i = 0; i < lista_coleta.size(); i++) {
			lista_coleta.get(i).setColmeia_id(deixaIdeNome(lista_coleta.get(i).getColmeia_id()));
		}
		return lista_coleta;
	}

	public Colmeia deixaIdeNome(Colmeia colmeia) {
		Colmeia colmeia_limpa = new Colmeia();
		colmeia_limpa.setId(colmeia.getId());
		colmeia_limpa.setNome(colmeia.getNome());
		colmeia_limpa.setCaixilho(null);
		return colmeia_limpa;
	}

	private void cleanColmeiaTipo() {
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).getColmeia_tipo_id().setCreated_at(null);
			lista.get(i).getColmeia_tipo_id().setDeleted_at(null);
			lista.get(i).getColmeia_tipo_id().setUpdated_at(null);
			lista.get(i).getColmeia_tipo_id().setLogin_alteracao(null);
			lista.get(i).getColmeia_tipo_id().setLogin_cadastro(null);
		}
	}

	private void cleanColmeiaOrigem() {
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).getColmeia_origem_id().setCreated_at(null);
			lista.get(i).getColmeia_origem_id().setDeleted_at(null);
			lista.get(i).getColmeia_origem_id().setUpdated_at(null);
			lista.get(i).getColmeia_origem_id().setLogin_alteracao(null);
			lista.get(i).getColmeia_origem_id().setLogin_cadastro(null);
		}
	}

	private void cleanColmeiaFundo() {
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).getColmeia_fundo_id().setCreated_at(null);
			lista.get(i).getColmeia_fundo_id().setDeleted_at(null);
			lista.get(i).getColmeia_fundo_id().setUpdated_at(null);
			lista.get(i).getColmeia_fundo_id().setLogin_alteracao(null);
			lista.get(i).getColmeia_fundo_id().setLogin_cadastro(null);
		}
	}

	// -------------------------------USERS-----------------------------------------
	// limpa Login de colmeias
	public List<Colmeia> cleanUser(List<Colmeia> lista) {
		this.lista = lista;
		for (int i = 0; i < lista.size(); i++) {

			lista.get(i).setLogin_alteracao(limpaLogin(lista.get(i).getLogin_alteracao()));
			lista.get(i).setLogin_cadastro(limpaLogin(lista.get(i).getLogin_cadastro()));
		}
		return this.lista;
	}

	public Users limpaLogin(Users users) {
		Users novo = new Users();
		novo.setId(users.getId());
		novo.setName(users.getName());
		novo.setConfirmed(null);
		novo.setAdmin(null);
		return novo;
	}
}
