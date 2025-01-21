package familias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import familias.modelo.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	//@Query("select u from usuario u where u.username= ?1 and u.password= ?2")
	//public Usuario buscarPorUsernameAndPassword (String username, String password);
	
	public Usuario findByUsernameAndPassword (String username, String password);

}
