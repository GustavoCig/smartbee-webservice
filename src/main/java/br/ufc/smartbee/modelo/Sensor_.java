package br.ufc.smartbee.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-05-03T15:53:37.818-0300")
@StaticMetamodel(Sensor.class)
public class Sensor_ {
	public static volatile SingularAttribute<Sensor, Integer> id;
	public static volatile SingularAttribute<Sensor, String> tipo;
	public static volatile SingularAttribute<Sensor, String> descricao;
	public static volatile SingularAttribute<Sensor, String> modelo;
	public static volatile SingularAttribute<Sensor, String> marca;
	public static volatile SingularAttribute<Sensor, Users> login_cadastro;
	public static volatile SingularAttribute<Sensor, Users> login_alteracao;
}
