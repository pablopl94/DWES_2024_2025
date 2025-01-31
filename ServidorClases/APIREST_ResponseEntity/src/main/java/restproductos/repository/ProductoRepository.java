package restproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restproductos.entidades.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	public List<Producto> findByFamilia_Codigo(int codigo);
	
	public List<Producto>findByMarcaAndColor(String marca, String color);
	
	public List<Producto> findByDescripcionContaining(String subcadena);
	
	
}
