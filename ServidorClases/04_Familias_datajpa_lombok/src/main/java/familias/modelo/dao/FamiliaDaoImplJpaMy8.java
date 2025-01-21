package familias.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import familias.modelo.entidades.Familia;
import familias.repository.FamiliaRepository;

@Repository

public class FamiliaDaoImplJpaMy8 implements FamiliaDao {

	@Autowired
	private FamiliaRepository frepo;

	@Override
	public Familia findOne(Integer clavePk) {
		return frepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Familia> buscarTodos() {
		return frepo.findAll();
	}

	@Override
	public Familia insertOne(Familia entidad) {

		try {
			return frepo.save(entidad);// ESTO ES VALIDAD CUANDO LA ID SE AUTOINCREMENTA++
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateOne(Familia entidad) {
		try {
			if (frepo.existsById(entidad.getIdFamilia())) {
				frepo.save(entidad);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if (frepo.existsById(clavePk)) {
				frepo.deleteById(clavePk);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
