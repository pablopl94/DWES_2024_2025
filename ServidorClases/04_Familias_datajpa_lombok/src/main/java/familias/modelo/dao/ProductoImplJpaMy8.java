package familias.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import familias.modelo.entidades.Producto;
import familias.repository.ProductoRepository;

@Repository
public class ProductoImplJpaMy8 implements ProductoDao{
	@Autowired
	private ProductoRepository prepo;
	
	@Override
	public Producto findOne(Long clavePk) {
		return prepo.findById(clavePk).orElse(null) ;
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto insertOne(Producto entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Producto entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Long clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Producto> buscarPorFamilia(int idFamilia) {
		return prepo.findByProductosPorFamilias(idFamilia);
	}

}
