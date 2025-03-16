package restproductos.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="Productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String descripcion;
	@Column(name="precio_unitario")
	private double precioUnitario;
	private String marca;
	private String color;
	@ManyToOne
	@JoinColumn(name="codigo_familia")
	private Familia familia;

}
