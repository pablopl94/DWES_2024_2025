package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query("from Movimiento m where m.cuenta.idCuenta = ?1")
	public List<Movimiento> findByMovByCuenta(int idCuenta);

}
