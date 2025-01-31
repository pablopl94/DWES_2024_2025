package restproductos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.entidades.Producto;
import restproductos.repository.ProductoRepository;

@Service
public class ProductosServicesImpl implements ProductoServices {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto findById(Integer ClavePk) {
		return productoRepository.findById(ClavePk).orElse(null);
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto updateOne(Producto entidad) {
		try {
			if(productoRepository.existsById(entidad.getCodigo())) {
				return productoRepository.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Producto insertOne(Producto entidad) {
		try {
			if(productoRepository.existsById(entidad.getCodigo())) {
				return null;
			} else {
				return productoRepository.save(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(Integer ClavePk) {
		try {
			if(productoRepository.existsById(ClavePk)) {
				productoRepository.deleteById(ClavePk);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Producto> findByFamilia(int codigo) {
		return productoRepository.findByFamilia_Codigo(codigo);
	}

	@Override
	public List<Producto> findByBrandAndColor(String marca, String color) {
		return productoRepository.findByMarcaAndColor(marca, color);
	}

	@Override
	public double findByAverageProdPriceByFamily(int codigoFamilia) {
	    List<Producto> listaProductoByFamilia = productoRepository.findByFamilia_Codigo(codigoFamilia);

	    return listaProductoByFamilia.stream()  // 1️ Convierte la lista en un Stream
	            .mapToDouble(Producto::getPrecioUnitario) // 2️ Extrae los precios de cada producto
	            .average() // 3️ Calcula el promedio de los precios
	            .orElse(0.0); // 4️ Si la lista está vacía, devuelve 0.0
	}

// FORMA TRADICIONAL DE HACER EL METODO CON UN FOR
//	@Override
//	public double findByAverageProdPriceByFamily(int codigoFamilia) {
//	    List<Producto> listaProductoByFamilia = productoRepository.findByFamilia_Codigo(codigoFamilia);
//
//	    // Si la lista está vacía, devolvemos 0.0 para evitar división por 0
//	    if (listaProductoByFamilia == null || listaProductoByFamilia.isEmpty()) {
//	        return 0.0;
//	    }
//
//	    // Variable para almacenar la suma de los precios
//	    double suma = 0.0;
//
//	    // Recorremos la lista con un bucle for tradicional
//	    for (Producto p : listaProductoByFamilia) {
//	        suma += p.getPrecioUnitario(); // Sumamos los precios de cada producto
//	    }
//
//	    // Calculamos el promedio dividiendo la suma entre la cantidad de productos
//	    return suma / listaProductoByFamilia.size();
//	}

	@Override
	public List<Producto> finfByDescriptionContainig(String subCadena) {
		return productoRepository.findByDescripcionContaining(subCadena);
	}

	
	

}
