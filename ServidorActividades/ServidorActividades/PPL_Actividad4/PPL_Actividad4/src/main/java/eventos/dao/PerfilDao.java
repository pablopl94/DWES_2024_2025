package eventos.dao;

import eventos.entidades.Perfil;

public interface PerfilDao extends IGenericoCrud<Perfil, Integer> {

	Perfil buscarPorNombre(String nombre);
}
