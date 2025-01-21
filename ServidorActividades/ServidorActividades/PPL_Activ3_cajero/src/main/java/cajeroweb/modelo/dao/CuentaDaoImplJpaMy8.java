package cajeroweb.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.repository.CuentaRepository;

@Repository

public class CuentaDaoImplJpaMy8 implements CuentaDao {
	
	@Autowired
	private CuentaRepository crepo;

	@Override
	public Cuenta findById(int idCuenta) {
		return crepo.findByIdCuenta(idCuenta);
	}

	@Override
	public Cuenta modificarCuenta(Cuenta cuenta, double cantidad, boolean ingreso) {
	   
	    double nuevoSaldo = ingreso ? cuenta.getSaldo() + cantidad : cuenta.getSaldo() - cantidad;
	    cuenta.setSaldo(nuevoSaldo);

	    return crepo.save(cuenta);
	}
	

}