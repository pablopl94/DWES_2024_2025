package familias.modelo.dao;

import java.util.List;

public interface IGenericoCrud <E,ID>{
	
	E findOne (ID clavePk);
	List<E> buscarTodos();
	E insertOne(E entidad);
	int updateOne (E entidad);
	int deleteOne (ID clavePk);
	
}
