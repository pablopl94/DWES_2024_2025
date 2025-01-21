package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoDao {
	
	int altaMovimiento(Movimiento mov);
	List<Movimiento> buscarMovPorCuenta(int idCuenta);

}
