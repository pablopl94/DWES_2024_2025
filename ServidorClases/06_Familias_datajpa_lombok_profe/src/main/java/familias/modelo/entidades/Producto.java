package familias.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


@Entity
@Table(name="productos")
public class Producto  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_producto")
	@EqualsAndHashCode.Include
	private long idProducto;
	private String descripcion;
	private double precio;
	private int stock;
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;

}
