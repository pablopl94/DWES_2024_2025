package eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.entidades.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
	
		Perfil findByNombre(String nombre);
}
