package restproductos.modelo.services;

import java.util.List;

import restproductos.modelo.entities.Familia;
import restproductos.modelo.entities.Producto;

public interface ProductoService {
	
	Producto alta(Producto producto);
	Producto modificar(Producto producto);
	int  eliminar(int codigo);
	Producto buscarUna(int codigo);
	List<Producto> buscarTodos();
	List<Producto> buscarPorFamilia(int codigoFamilia);
	List<Producto> buscarPorMarcaYColor(String marca, String color);
	double mediaPrecioProdPorFamilia(int codigoFamilia);
	List<Producto> sbCadenaDeDescripcion(String subCadena);
	
	
	
	

}
