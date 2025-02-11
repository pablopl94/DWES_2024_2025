package eventos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "EVENTOS")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EVENTO")
	private int idEvento;

	@Column(name = "AFORO_MAXIMO")
	private int aforoMaximo;

	private String descripcion;

	private String destacado;

	private String direccion;

	private int duracion;

	private String estado;

	@Column(name = "FECHA_INICIO")
	private LocalDate fechaInicio;

	@Column(name = "MINIMO_ASISTENCIA")
	private int minimoAsistencia;

	private String nombre;

	private BigDecimal precio;

	// uni-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name = "ID_TIPO")
	private Tipo tipo;
}
