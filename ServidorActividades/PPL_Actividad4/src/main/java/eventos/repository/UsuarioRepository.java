package eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{


}
