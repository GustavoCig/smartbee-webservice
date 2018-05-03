package br.ufc.smartbee.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.042-0300")
@StaticMetamodel(Colmeia_coleta.class)
public class Colmeia_coleta_ {
	public static volatile SingularAttribute<Colmeia_coleta, Integer> id;
	public static volatile SingularAttribute<Colmeia_coleta, Colmeia> colmeia_id;
	public static volatile SingularAttribute<Colmeia_coleta, Sensor> sensor_id;
	public static volatile SingularAttribute<Colmeia_coleta, Calendar> created_at;
	public static volatile SingularAttribute<Colmeia_coleta, String> valor;
}
