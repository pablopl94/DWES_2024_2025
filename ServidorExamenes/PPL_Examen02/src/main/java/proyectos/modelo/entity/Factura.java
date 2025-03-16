package proyectos.modelo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="facturas")

public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_factura")
	private String idFactura;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="id_proyecto")
	private Proyecto proyecto;

}
