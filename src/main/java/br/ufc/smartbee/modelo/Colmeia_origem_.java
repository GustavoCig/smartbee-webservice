package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.053-0300")
@StaticMetamodel(Colmeia_origem.class)
public class Colmeia_origem_ {
	public static volatile SingularAttribute<Colmeia_origem, Long> id;
	public static volatile SingularAttribute<Colmeia_origem, String> nome;
	public static volatile SingularAttribute<Colmeia_origem, String> descricao;
	public static volatile SingularAttribute<Colmeia_origem, Calendar> created_at;
	public static volatile SingularAttribute<Colmeia_origem, Calendar> updated_at;
	public static volatile SingularAttribute<Colmeia_origem, Calendar> deleted_at;
	public static volatile SingularAttribute<Colmeia_origem, Users> login_cadastro;
	public static volatile SingularAttribute<Colmeia_origem, Users> login_alteracao;
}
