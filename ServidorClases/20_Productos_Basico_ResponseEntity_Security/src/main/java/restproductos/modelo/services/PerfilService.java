package restproductos.modelo.services;

import restproductos.modelo.entities.Perfil;

public interface PerfilService {
	
	Perfil buscarUno(int idPerfil);
	Perfil buscarPorNombre(String nombre);

}
