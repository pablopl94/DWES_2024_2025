package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Cuenta;

public interface CuentaDao {

	Cuenta findById (int idCuenta);
	
	Cuenta modificarCuenta (Cuenta cuenta, double cantidad, boolean ingreso);

	
	
}
