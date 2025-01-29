package restproductos.services;

import java.util.List;

import restproductos.entidades.Producto;

public interface ProductoServices extends IGenericCrud<Producto, Integer> {

	List<Producto> findByFamilia(int codigo);
	List<Producto> findByBrandAndColor(String marca, String color);
	double findByAverageProdPriceByFamily(int codigoFamilia);
	List<Producto> finfByDescriptionContainig (String subCadena);
}
