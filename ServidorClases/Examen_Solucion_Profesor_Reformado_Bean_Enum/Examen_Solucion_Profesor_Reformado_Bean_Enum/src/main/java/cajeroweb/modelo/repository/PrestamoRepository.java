package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cajeroweb.modelo.entidades.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo,	Integer>{
	
	@Query("select p from Prestamo p where p.cuenta.idCuenta = ?1")
	public List<Prestamo> findByCuenta(int idCuenta);
	
	public List<Prestamo> findByCuentaIdCuenta(int idCuenta);

}
