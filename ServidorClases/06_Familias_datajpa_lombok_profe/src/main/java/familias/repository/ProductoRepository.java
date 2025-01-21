package familias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import familias.modelo.entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.familia.idFamilia = ?1")
	public List<Producto> findByProductosPorFamilia(int idFamilia);

}
