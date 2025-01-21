package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	public List<Prestamo> findByCuenta (Cuenta cuenta);
}
