package es.unir.excursiones.web.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unir.excursiones.web.modelo.entidades.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion, Integer> {

	// CON CONSULTAS
	
	@Query("select e from Excursion e where e.estado like ?1")
	public List<Excursion> findByActivos(String estado);

	@Query("select e from Excursion e where e.destacado like ?1")
	public List<Excursion> findByDestacado(String destacado);

	// CON METODOS DERIVADOS
	
	public List<Excursion> findByPrecioUnitarioLessThan(double precioMax);

	public List<Excursion> findByPrecioUnitarioGreaterThan(double precioMin);

	public List<Excursion> findByOrigenOrDestinoContaining(String origen, String destino);

	public List<Excursion> findByPrecioUnitarioBetween(double precioMin, double precioMax);

}
