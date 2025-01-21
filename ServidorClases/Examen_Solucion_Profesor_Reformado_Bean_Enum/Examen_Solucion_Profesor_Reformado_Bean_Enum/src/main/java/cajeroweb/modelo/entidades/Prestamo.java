package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prestamo")
	private int idPrestamo;
	private String descripcion;
	@Column(name="cantidad_prestamo")
	private double cantidadPrestamo;
	@Column(name="fecha_prestamo")
	@Temporal(TemporalType.DATE)
	private Date fechaPrestamo;
	@Column(name="tasa_interes_anual")
	private double tasaInteresAnual;
	@Column(name="plazo_meses")
	private int plazoMeses;
	@Column(name="tipo_cuota")
	@Enumerated(EnumType.STRING)
	private TipoCuota tipoCuota;
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

}
