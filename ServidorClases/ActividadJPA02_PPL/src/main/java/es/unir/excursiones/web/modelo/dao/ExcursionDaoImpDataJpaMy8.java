package es.unir.excursiones.web.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.unir.excursiones.web.modelo.entidades.Excursion;
import es.unir.excursiones.web.modelo.repository.ExcursionRepository;

@Repository
public class ExcursionDaoImpDataJpaMy8 implements ExcursionDao {

	@Autowired
	private ExcursionRepository prepo;

	@Override
	public Excursion findById(int idExcursion) {
		return prepo.findById(idExcursion).orElse(null);
	}

	@Override
	public int insertOne(Excursion excursion) {

		try {
			Excursion excursionNew = prepo.save(excursion);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateOne(Excursion excursion) {
		try {
			if (prepo.existsById(excursion.getIdExcursion())) {
				prepo.save(excursion);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 0;
	}

	@Override
	public List<Excursion> findAll() {
		return prepo.findAll();
	}

	@Override
	public List<Excursion> findByActivos() {
		return prepo.findByActivos("CREADO");
	}

	@Override
	public List<Excursion> findByTerminados() {
		return prepo.findByActivos("TERMINADO");
	}

	@Override
	public List<Excursion> findByDestacados() {
		return prepo.findByDestacado("S");

	}

	@Override
	public List<Excursion> findByPrecioMayorQue(double precioMin) {
		
		return prepo.findByPrecioUnitarioGreaterThan(precioMin);
	}

	@Override
	public List<Excursion> findByPrecioMenorQue(double precioMax) {
		
		return prepo.findByPrecioUnitarioLessThan(precioMax);
	}

	@Override
	public List<Excursion> findByPrecioEntre(double precioMin, double precioMax) {
		
		return prepo.findByPrecioUnitarioBetween(precioMin, precioMax);
	}

	@Override
	public List<Excursion> findByOrigenDestino(String palabra) {
		
		return prepo.findByOrigenOrDestinoContaining(palabra, palabra);
	}

}