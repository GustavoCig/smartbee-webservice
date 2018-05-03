package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.034-0300")
@StaticMetamodel(Cliente.class)
public class Cliente_ {
	public static volatile SingularAttribute<Cliente, Integer> idApiario;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, String> email;
	public static volatile SingularAttribute<Cliente, String> endereco;
	public static volatile SingularAttribute<Cliente, String> telefone;
	public static volatile SingularAttribute<Cliente, String> celular;
	public static volatile SingularAttribute<Cliente, Calendar> created_at;
	public static volatile SingularAttribute<Cliente, Calendar> updated_at;
	public static volatile SingularAttribute<Cliente, Calendar> deleted_at;
	public static volatile SingularAttribute<Cliente, Users> login_cadastro;
	public static volatile SingularAttribute<Cliente, Users> login_alteracao;
}
