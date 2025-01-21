package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

import cajeroweb.modelo.entidades.Movimiento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "movimientos")
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	//CLAVEPK
	@EqualsAndHashCode.Include
	@Id
	@Column(name="id_movimiento")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idMovimiento;
    
    //OTROS ATRIBUTOS
    private LocalDateTime fecha;
    private double cantidad;
    private String operacion;
    
    //CLAVE FK
    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;
}