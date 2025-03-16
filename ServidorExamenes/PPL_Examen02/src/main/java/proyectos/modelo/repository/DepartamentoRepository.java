package proyectos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectos.modelo.entity.Cliente;
import proyectos.modelo.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{

}
