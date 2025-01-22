package eventos.dao;

import java.util.List;

public interface IGenericoCrud<E,ID> {
	
	E findById (ID clavePk);
	List<E> findAll();
	int updateOne(E entidad);
	int insertOne(E entidad);
	int deleteOne(ID clavePk);

}


