package restproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restproductos.modelo.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	public List<Producto> findByMarcaAndColor(String marca, String color);
	
	@Query("from Producto p where p.familia.codigo = ?1")
	public List<Producto> findByFamilia(int codigo);
	
	public List<Producto> findByDescripcionContaining(String subcadena);
	

}
