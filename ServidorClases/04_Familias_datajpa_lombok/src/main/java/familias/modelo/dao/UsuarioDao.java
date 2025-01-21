package familias.modelo.dao;

import familias.modelo.entidades.Usuario;

public interface UsuarioDao {

	Usuario login ( String username, String password);
	Usuario registrar ( Usuario usuario);
	
}
