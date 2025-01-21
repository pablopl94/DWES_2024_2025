package cajeroweb.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Movimiento;
import cajeroweb.modelo.repository.MovimientoRepository;
@Repository
public class MovimientoDaoImplJpaMy8 implements MovimientoDao{
	@Autowired
	private MovimientoRepository mrepo;
	@Override
	public int altaMovimiento(Movimiento mov) {
		try {
			
				mrepo.save(mov);
				return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Movimiento> buscarMovPorCuenta(int idCuenta) {
		// TODO Auto-generated method stub
		return mrepo.findByMovByCuenta(idCuenta);
	}

}
