package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	public Usuario findByUsername(String usename);

}
