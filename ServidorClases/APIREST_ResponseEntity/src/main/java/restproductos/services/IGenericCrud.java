package restproductos.services;

import java.util.List;

public interface IGenericCrud <E, ID> {
	
	E findById (ID ClavePk);
	List<E> findAll ();
	E updateOne (E entidad);
	E insertOne (E entidad);
	int deleteOne (ID ClavePk);
	

}
