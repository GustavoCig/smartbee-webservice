package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.068-0300")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, Integer> id;
	public static volatile SingularAttribute<Users, String> name;
	public static volatile SingularAttribute<Users, String> email;
	public static volatile SingularAttribute<Users, String> address;
	public static volatile SingularAttribute<Users, String> phone;
	public static volatile SingularAttribute<Users, String> cellphone;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, Integer> confirmed;
	public static volatile SingularAttribute<Users, Integer> admin;
	public static volatile SingularAttribute<Users, Calendar> last_login;
	public static volatile SingularAttribute<Users, String> remember_token;
	public static volatile SingularAttribute<Users, Calendar> created_at;
	public static volatile SingularAttribute<Users, Calendar> updated_at;
}
