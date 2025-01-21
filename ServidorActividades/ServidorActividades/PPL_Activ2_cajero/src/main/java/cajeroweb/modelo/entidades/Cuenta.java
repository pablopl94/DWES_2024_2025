package cajeroweb.modelo.entidades;

import java.io.Serializable;

import cajeroweb.modelo.entidades.Cuenta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//CLAVE PK
	@EqualsAndHashCode.Include
	@Id
	@Column(name="id_cuenta")
	private int idCuenta;
	
	//OTROS ATRIBUTOS
	private double saldo;
	@Column(name="tipo_cuenta")
	private String tipoCuenta;

}