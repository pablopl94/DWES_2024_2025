package productos.web.modelo.dao;

import java.util.List;

import productos.web.modelo.entidades.Producto;


public interface ProductoDao {
	
	Producto findById(long idProducto);
	int insertOne( Producto producto ); //Producto insertOne( Producto producto );  --> buena practica
	int updateOne( Producto producto);
	int deleteOne( Producto producto);
	int deleteOne (long idproducto);
	List<Producto> findAll();
	List<Producto> findByDescripcionProducto(String cadena);
}
