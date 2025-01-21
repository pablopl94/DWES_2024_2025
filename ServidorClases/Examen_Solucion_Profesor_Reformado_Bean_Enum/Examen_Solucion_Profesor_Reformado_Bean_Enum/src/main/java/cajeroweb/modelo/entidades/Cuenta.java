package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@NoArgsConstructor
@AllArgsConstructor
@Data
*/
@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_cuenta")
	private int idCuenta;
	private double saldo;
	@Column(name="tipo_cuenta")
	private String tipoCuenta;
	
	public void ingresar(double importe) {
		saldo += importe;
	}
	public boolean extraer(double importe) {
		if (importe > saldo)
			return false;
		saldo -= importe;
		return true;
	}
	
	public boolean transferir(Cuenta cuentaDestino, double importe) {
		if (extraer(importe)) {
			cuentaDestino.ingresar(importe);
			return true;
			
		}
		return false;
	}
	public Cuenta(int idCuenta, double saldo, String tipoCuenta) {
		super();
		this.idCuenta = idCuenta;
		this.saldo = saldo;
		this.tipoCuenta = tipoCuenta;
	}
	public Cuenta() {
		super();
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", saldo=" + saldo + ", tipoCuenta=" + tipoCuenta + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idCuenta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cuenta))
			return false;
		Cuenta other = (Cuenta) obj;
		return idCuenta == other.idCuenta;
	}
	
	

}
