package restproductos.modelo.services;

import java.util.List;

import restproductos.modelo.entities.Familia;

public interface FamiliaServices {
	
	Familia alta(Familia familia);
	Familia modificar(Familia familia);
	int  eliminar(int codigo);
	Familia buscarUna(int codigo);
	List<Familia> buscarTodos();

}
