package com.empresa.services;

import java.util.List;

public interface IGenericCrud <E,ID>{
	
	E findById (ID clavePk);
	List<E> findAll ();
	E insertOne (E entidad);
	E updateOne (E entidad);
	int deleteOne (ID clavePk);

}
