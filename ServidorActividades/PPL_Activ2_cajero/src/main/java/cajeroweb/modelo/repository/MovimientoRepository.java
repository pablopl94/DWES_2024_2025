package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{

	public List<Movimiento> findByCuenta (Cuenta cuenta);
}
