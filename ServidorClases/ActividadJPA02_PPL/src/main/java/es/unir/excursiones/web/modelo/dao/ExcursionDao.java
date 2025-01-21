package es.unir.excursiones.web.modelo.dao;

import java.util.List;

import es.unir.excursiones.web.modelo.entidades.Excursion;


public interface ExcursionDao {
	
	Excursion findById(int idExcursion);
	int insertOne( Excursion excursion );
	int updateOne( Excursion excursion);
	List<Excursion> findAll();
	List<Excursion> findByActivos();
	List<Excursion> findByTerminados();
	List<Excursion> findByDestacados();
	//OTROS 
	List<Excursion> findByPrecioMayorQue(double precioMin);
    List<Excursion> findByPrecioMenorQue(double precioMax);
    List<Excursion> findByPrecioEntre(double precioMin, double precioMax);
    List<Excursion> findByOrigenDestino(String palabra);
    
	
}
