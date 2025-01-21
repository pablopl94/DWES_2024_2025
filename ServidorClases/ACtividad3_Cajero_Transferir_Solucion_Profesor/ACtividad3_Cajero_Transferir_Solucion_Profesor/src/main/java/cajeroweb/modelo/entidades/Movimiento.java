package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/*
@NoArgsConstructor
@AllArgsConstructor
@Data
*/
@Entity
@Table(name="movimientos")
public class Movimiento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_movimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha; //DATETIME,
	private double cantidad;
	private String operacion;
	
	public Movimiento(int idMovimiento, Cuenta cuenta, Date fecha, double cantidad, String operacion) {
		super();
		this.idMovimiento = idMovimiento;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}
	public Movimiento() {
		super();
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@Override
	public String toString() {
		return "Movimiento [idMovimiento=" + idMovimiento + ", cuenta=" + cuenta + ", fecha=" + fecha + ", cantidad="
				+ cantidad + ", operacion=" + operacion + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idMovimiento);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Movimiento))
			return false;
		Movimiento other = (Movimiento) obj;
		return idMovimiento == other.idMovimiento;
	}
	
	

}
