package productos.web.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import productos.web.modelo.entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.descripcion like ?1 " )
	public List<Producto> findByDescripcionProducto(String cadena);
	
	//	METODOS DERIVADOS
	
	public List<Producto>findByPrecioGreaterThanAndStockLessThan(double precio, int stock);
	
	public List<Producto> findByDescripcionContaining(String cadena);
}
