package proyectos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectos.modelo.entity.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, String> {

    Factura findByProyecto_IdProyecto(String idProyecto);

}
