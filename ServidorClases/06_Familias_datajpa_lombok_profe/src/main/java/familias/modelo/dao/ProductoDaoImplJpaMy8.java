package familias.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import familias.modelo.entidades.Producto;
import familias.repository.ProductoRepository;

@Repository
public class ProductoDaoImplJpaMy8  implements ProductoDao{

	@Autowired
	private ProductoRepository prepo;
	
	
	@Override
	public Producto buscarUno(Long clavePk) {
		// TODO Auto-generated method stub
		return prepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto insertUno(Producto entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUno(Producto entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUno(Long clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Producto> buscarPorFamilia(int idFamilia) {
		// TODO Auto-generated method stub
		return prepo.findByProductosPorFamilia(idFamilia);
	}

}
