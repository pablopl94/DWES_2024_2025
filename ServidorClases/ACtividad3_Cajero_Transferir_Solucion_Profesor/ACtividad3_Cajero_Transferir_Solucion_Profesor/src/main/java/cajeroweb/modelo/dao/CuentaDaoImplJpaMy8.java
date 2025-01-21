package cajeroweb.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.repository.CuentaRepository;
@Repository
public class CuentaDaoImplJpaMy8 implements CuentaDao{
	@Autowired
	private CuentaRepository crepo;
	@Override
	public int modificarCuenta(Cuenta cuenta) {
		try {
			crepo.save(cuenta);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Cuenta buscarUna(int idCuenta) {
		// TODO Auto-generated method stub
		return crepo.findById(idCuenta).orElse(null);
	}

}
