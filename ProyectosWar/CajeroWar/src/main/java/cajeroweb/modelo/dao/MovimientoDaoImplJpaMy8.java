package cajeroweb.modelo.dao;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;
import cajeroweb.modelo.repository.MovimientoRepository;

@Repository

public class MovimientoDaoImplJpaMy8 implements MovimientoDao{

	@Autowired
	private MovimientoRepository mrepo;
	
	@Override
	public List<Movimiento> buscarMovimientoPorCuenta(Cuenta cuenta){
		// TODO Auto-generated method stub
		return mrepo.findByCuenta(cuenta);
	}

	
	@Override
	public Movimiento registrarMovimiento(Cuenta cuenta, double cantidad, String operacion) {
		
		Movimiento movimiento = new Movimiento();
        movimiento.setOperacion(operacion);
        movimiento.setCantidad(cantidad);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        return mrepo.save(movimiento);
    }


}
