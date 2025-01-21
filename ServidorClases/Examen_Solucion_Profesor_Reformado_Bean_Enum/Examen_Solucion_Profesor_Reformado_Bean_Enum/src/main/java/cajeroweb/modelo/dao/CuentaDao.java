package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Cuenta;

public interface CuentaDao {
	
	int modificarCuenta(Cuenta cuenta);
	Cuenta buscarUna(int idCuenta);

}
