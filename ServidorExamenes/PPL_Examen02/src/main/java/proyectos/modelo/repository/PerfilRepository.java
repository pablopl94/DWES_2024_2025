package proyectos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectos.modelo.entity.Cliente;
import proyectos.modelo.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
