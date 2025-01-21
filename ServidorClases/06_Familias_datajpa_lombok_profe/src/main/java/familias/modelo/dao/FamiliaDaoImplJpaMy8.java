package familias.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import familias.modelo.entidades.Familia;
import familias.repository.FamiliaRepository;
@Repository
public class FamiliaDaoImplJpaMy8 implements FamiliaDao{
	
	@Autowired
	private FamiliaRepository frepo;

	@Override
	public Familia buscarUno(Integer clavePk) {
		// TODO Auto-generated method stub
		return frepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Familia> buscarTodos() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}

	@Override
	public Familia insertUno(Familia entidad) {
		try {
		return frepo.save(entidad);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUno(Familia entidad) {
		try {
		if (frepo.existsById(entidad.getIdFamilia())) {
			frepo.save(entidad);
			return 1;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUno(Integer clavePk) {
		try {
			if (frepo.existsById(clavePk)) {
				frepo.deleteById(clavePk);
				return 1;
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
	}
	
	

}
