package familias.modelo.dao;

import java.util.List;

public interface IGenericoCrud<E,ID> {
	
	E buscarUno(ID clavePk);
	List<E> buscarTodos();
	E insertUno(E entidad);
	int updateUno(E entidad);
	int deleteUno(ID clavePk);

}
