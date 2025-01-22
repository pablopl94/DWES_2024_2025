package es.unir.excursionweb.model.dao;

import java.util.List;

import es.unir.excursionweb.model.javabean.Excursion;

public interface ExcursionDao {
	
	Excursion findById(int idExcursion);
	int insertOne( Excursion excursion );
	int updateOne( Excursion excursion);
	List<Excursion> findAll();
	List<Excursion> findByActivos();
	List<Excursion> findByTerminados();
	List<Excursion> findByDestacados();
	List<Excursion> findByPrecioMayorQue(double precioMin);
    List<Excursion> findByPrecioMenorQue(double precioMax);
    List<Excursion> findByPrecioEntre(double precioMin, double precioMax);
    List<Excursion> findByOrigenDestino(String palabra);
	
}
