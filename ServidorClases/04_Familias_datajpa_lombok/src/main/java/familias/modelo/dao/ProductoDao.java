package familias.modelo.dao;

import java.util.List;

import familias.modelo.entidades.Producto;

public interface ProductoDao extends IGenericoCrud<Producto, Long> {

	List<Producto> buscarPorFamilia(int idFamilia);

}
