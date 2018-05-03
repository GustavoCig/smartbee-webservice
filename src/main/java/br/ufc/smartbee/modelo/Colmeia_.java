package br.ufc.smartbee.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-02-22T18:02:55.037-0300")
@StaticMetamodel(Colmeia.class)
public class Colmeia_ {
	public static volatile SingularAttribute<Colmeia, Long> id;
	public static volatile SingularAttribute<Colmeia, String> nome;
	public static volatile SingularAttribute<Colmeia, Integer> caixilho;
	public static volatile SingularAttribute<Colmeia, String> latitude;
	public static volatile SingularAttribute<Colmeia, String> longitude;
	public static volatile SingularAttribute<Colmeia, Colmeia_tipo> colmeia_tipo;
	public static volatile SingularAttribute<Colmeia, Colmeia_fundo> colmeia_fundo;
	public static volatile SingularAttribute<Colmeia, Colmeia_origem> colmeia_origem;
	public static volatile SingularAttribute<Colmeia, Users> login_cadastro;
	public static volatile SingularAttribute<Colmeia, Users> login_alteracao;
}
