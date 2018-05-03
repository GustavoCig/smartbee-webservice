package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-02T15:22:24.803-0300")
@StaticMetamodel(Apiario.class)
public class Apiario_ {
	public static volatile SingularAttribute<Apiario, Integer> id;
	public static volatile SingularAttribute<Apiario, String> nome;
	public static volatile SingularAttribute<Apiario, String> endereco;
	public static volatile SingularAttribute<Apiario, String> latitude;
	public static volatile SingularAttribute<Apiario, String> longitude;
	public static volatile SingularAttribute<Apiario, String> descricao;
	public static volatile SingularAttribute<Apiario, Users> login_cadastro;
	public static volatile SingularAttribute<Apiario, Users> login_alteracao;
	public static volatile SingularAttribute<Apiario, Calendar> created_at;
	public static volatile SingularAttribute<Apiario, Calendar> updated_at;
	public static volatile SingularAttribute<Apiario, Calendar> deleted_at;
}
