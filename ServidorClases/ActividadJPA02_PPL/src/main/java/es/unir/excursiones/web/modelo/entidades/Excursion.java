package es.unir.excursiones.web.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="excursiones")
public class Excursion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_excursion")
	private int idExcursion;
	private String origen;
	private String destino;

	@Column(name = "fecha_excursion")
	private LocalDate fechaExcursion; 

	private int duracion;
	private String estado;
	private String destacado;

	@Column(name = "aforo_maximo")
	private int aforoMaximo;

	@Column(name = "mini_asistentes")
	private int miniAsistentes;

	@Column(name = "precio_unitario")
	private double precioUnitario;

	private String image;

	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;  

	
	

	
	// CONSTRUCTORES
	
    public Excursion() {
    }


	public Excursion(int idExcursion, String origen, String destino, LocalDate fechaExcursion, int duracion,
			String estado, String destacado, int aforoMaximo, int miniAsistentes, double precioUnitario, String image,
			LocalDate fechaAlta) {
		super();
		this.idExcursion = idExcursion;
		this.origen = origen;
		this.destino = destino;
		this.fechaExcursion = fechaExcursion;
		this.duracion = duracion;
		this.estado = estado;
		this.destacado = destacado;
		this.aforoMaximo = aforoMaximo;
		this.miniAsistentes = miniAsistentes;
		this.precioUnitario = precioUnitario;
		this.image = image;
		this.fechaAlta = fechaAlta;
	}

	// GETTER AND SETTERS
	
	public int getIdExcursion() {
		return idExcursion;
	}


	public void setIdExcursion(int idExcursion) {
		this.idExcursion = idExcursion;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public LocalDate getFechaExcursion() {
		return fechaExcursion;
	}


	public void setFechaExcursion(LocalDate fechaExcursion) {
		this.fechaExcursion = fechaExcursion;
	}


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getDestacado() {
		return destacado;
	}


	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}


	public int getAforoMaximo() {
		return aforoMaximo;
	}


	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}


	public int getMiniAsistentes() {
		return miniAsistentes;
	}


	public void setMiniAsistentes(int miniAsistentes) {
		this.miniAsistentes = miniAsistentes;
	}


	public double getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	// HASCODE Y EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(idExcursion);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Excursion other = (Excursion) obj;
		return idExcursion == other.idExcursion;
	}

	

	// TO STRING
	
	@Override
	public String toString() {
		return "Excursion [idExcursion=" + idExcursion + ", origen=" + origen + ", destino=" + destino
				+ ", fechaExcursion=" + fechaExcursion + ", duracion=" + duracion + ", estado=" + estado
				+ ", destacado=" + destacado + ", aforoMaximo=" + aforoMaximo + ", miniAsistentes=" + miniAsistentes
				+ ", precioUnitario=" + precioUnitario + ", image=" + image + ", fechaAlta=" + fechaAlta + "]";
	}

		
	
}