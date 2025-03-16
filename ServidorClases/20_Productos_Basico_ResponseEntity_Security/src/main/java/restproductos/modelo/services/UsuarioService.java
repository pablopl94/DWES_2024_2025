package restproductos.modelo.services;

import restproductos.modelo.entities.Usuario;

public interface UsuarioService {
	
	Usuario buscarUno(String username);
	Usuario buscarEmail(String email);

}
