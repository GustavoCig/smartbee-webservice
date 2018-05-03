package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.052-0300")
@StaticMetamodel(Colmeia_fundo.class)
public class Colmeia_fundo_ {
	public static volatile SingularAttribute<Colmeia_fundo, Long> id;
	public static volatile SingularAttribute<Colmeia_fundo, String> nome;
	public static volatile SingularAttribute<Colmeia_fundo, String> descricao;
	public static volatile SingularAttribute<Colmeia_fundo, Calendar> created_at;
	public static volatile SingularAttribute<Colmeia_fundo, Calendar> updated_at;
	public static volatile SingularAttribute<Colmeia_fundo, Calendar> deleted_at;
	public static volatile SingularAttribute<Colmeia_fundo, Users> login_cadastro;
	public static volatile SingularAttribute<Colmeia_fundo, Users> login_alteracao;
}
