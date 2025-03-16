package proyectos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectos.modelo.entity.ProyectoConEmpleados;
import java.util.List;

public interface ProyectoConEmpleadosRepository extends JpaRepository<ProyectoConEmpleados, Integer> {

    List<ProyectoConEmpleados> findByProyecto_IdProyecto(String idProyecto);

}
