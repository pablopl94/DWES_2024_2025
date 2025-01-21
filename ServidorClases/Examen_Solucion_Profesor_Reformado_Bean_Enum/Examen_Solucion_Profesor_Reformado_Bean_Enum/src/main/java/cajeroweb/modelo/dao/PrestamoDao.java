package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Prestamo;

public interface PrestamoDao {
	
	Prestamo altaPrestamo(Prestamo prestamo);
	List<Prestamo> buscarPorCuenta(int idCuenta);
	int eliminarPrestamo(int idPrestamo);

}
