package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.055-0300")
@StaticMetamodel(Colmeia_tipo.class)
public class Colmeia_tipo_ {
	public static volatile SingularAttribute<Colmeia_tipo, Long> id;
	public static volatile SingularAttribute<Colmeia_tipo, String> nome;
	public static volatile SingularAttribute<Colmeia_tipo, String> descricao;
	public static volatile SingularAttribute<Colmeia_tipo, Calendar> created_at;
	public static volatile SingularAttribute<Colmeia_tipo, Calendar> updated_at;
	public static volatile SingularAttribute<Colmeia_tipo, Calendar> deleted_at;
	public static volatile SingularAttribute<Colmeia_tipo, Users> login_cadastro;
	public static volatile SingularAttribute<Colmeia_tipo, Users> login_alteracao;
}
