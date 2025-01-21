package cajeroweb.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.repository.PrestamoRepository;

@Repository
public class PrestamoDaoImplJpaMy8 implements PrestamoDao{
	
	@Autowired 
	private PrestamoRepository prepo;

	@Override
	public List<Prestamo> buscarPrestamoPorCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return prepo.findByCuenta(cuenta);
	}

	@Override
	public Prestamo registrarPrestamo(Cuenta cuenta, String descripcion, double cantidadPrestamo,
			double tasaInteresAnual, int plazoMeses, String tipoCuota) {
	    Prestamo prestamo = new Prestamo();
	    prestamo.setDescripcion(descripcion);
	    prestamo.setCantidadPrestamo(cantidadPrestamo);
	    prestamo.setTasaInteresAnual(tasaInteresAnual);
	    prestamo.setPlazoMeses(plazoMeses);
	    prestamo.setTipoCuota(tipoCuota);
	    prestamo.setFechaPrestamo(java.time.LocalDate.now());
	    prestamo.setCuenta(cuenta);
	    return prepo.save(prestamo);
	}
	
	@Override
	public Prestamo insertUno(Prestamo prestamo) {
	    try {
	        return prepo.save(prestamo); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // Devuelve null si ocurre un error
	}

	@Override
	public int updateUno(Prestamo prestamo) {
	    try {
	        if (prepo.existsById(prestamo.getIdPrestamo())) { 
	            prepo.save(prestamo);
	            return 1; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0; // Devuelve 0 si no se encuentra el préstamo o hay un error
	}

	@Override
	public int deleteUno(Integer idPrestamo) {
	    try {
	        if (prepo.existsById(idPrestamo)) { 
	            prepo.deleteById(idPrestamo); 
	            return 1; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0; 
	}


	
}
