package proyectos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectos.modelo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
