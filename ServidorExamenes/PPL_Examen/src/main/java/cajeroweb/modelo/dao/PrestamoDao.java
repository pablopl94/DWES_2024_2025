package cajeroweb.modelo.dao;

import java.util.List;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;



public interface PrestamoDao {
	
	List<Prestamo> buscarPrestamoPorCuenta(Cuenta cuenta);


	Prestamo registrarPrestamo(Cuenta cuenta, String descripcion, double cantidadPrestamo, double tasaInteresAnual,
			int plazoMeses, String tipoCuota);


	int deleteUno(Integer idPrestamo);


	Prestamo insertUno(Prestamo prestamo);


	int updateUno(Prestamo prestamo);
}


