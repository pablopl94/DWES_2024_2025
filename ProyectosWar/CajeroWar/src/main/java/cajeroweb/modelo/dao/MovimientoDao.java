package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoDao {


	List<Movimiento> buscarMovimientoPorCuenta(Cuenta cuenta);

	Movimiento registrarMovimiento(Cuenta cuenta, double cantidad, String operacion);
}
