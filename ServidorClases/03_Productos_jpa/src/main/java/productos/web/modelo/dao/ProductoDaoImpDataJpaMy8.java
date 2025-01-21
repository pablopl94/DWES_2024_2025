package productos.web.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import productos.web.modelo.entidades.Producto;
import productos.web.modelo.repository.ProductoRepository;

@Repository
public class ProductoDaoImpDataJpaMy8 implements ProductoDao {

	@Autowired
	private ProductoRepository prepo;

	@Override
	public Producto findById(long idProducto) {

		return prepo.findById(idProducto).orElse(null);
	}

	@Override
	public int insertOne(Producto producto) {

		try {
			Producto productoNew = prepo.save(producto);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		// SIN EXCEPCION
		// Producto productoNew = prepo.save(producto);
		// return (prepo.save(producto) != null ? 1 : 0 );
	}

	@Override
	public int updateOne(Producto producto) {
		try {
			if (prepo.existsById(producto.getIdProducto())) {
				prepo.save(producto);
				return 1;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 0;
	}

	@Override
	public int deleteOne(Producto producto) {
		try {
			if (prepo.existsById(producto.getIdProducto())) {
				prepo.deleteById((producto.getIdProducto()));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}

	@Override
	public int deleteOne(long idproducto) {
		try {
			if (prepo.existsById(idproducto)) {
			    prepo.deleteById(idproducto);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
	
	@Override
	public List<Producto> findAll() {
		return prepo.findAll();
	}

	@Override
	public List<Producto> findByDescripcionProducto(String cadena) {
		return prepo.findByDescripcionProducto( "%" + cadena + "%");
	}
	
	
	

}
