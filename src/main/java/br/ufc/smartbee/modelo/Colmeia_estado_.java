package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.050-0300")
@StaticMetamodel(Colmeia_estado.class)
public class Colmeia_estado_ {
	public static volatile SingularAttribute<Colmeia_estado, Long> id;
	public static volatile SingularAttribute<Colmeia_estado, String> nome;
	public static volatile SingularAttribute<Colmeia_estado, String> descricao;
	public static volatile SingularAttribute<Colmeia_estado, Calendar> created_at;
	public static volatile SingularAttribute<Colmeia_estado, Calendar> updated_at;
	public static volatile SingularAttribute<Colmeia_estado, Calendar> deleted_at;
	public static volatile SingularAttribute<Colmeia_estado, Users> login_cadastro;
	public static volatile SingularAttribute<Colmeia_estado, Users> login_alteracao;
}
