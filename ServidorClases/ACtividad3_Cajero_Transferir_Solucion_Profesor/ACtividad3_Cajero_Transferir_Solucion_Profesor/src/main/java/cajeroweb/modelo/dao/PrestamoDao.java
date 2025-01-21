package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Movimiento;

public interface PrestamoDao {
	

	List<Movimiento> buscarMovPorCuenta(int idCuenta);

}
