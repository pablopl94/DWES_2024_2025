package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Perfil;
import eventos.repository.PerfilRepository;

@Repository
public class PerfilDaoImplListMy8 implements PerfilDao{

	@Autowired
	private PerfilRepository prepo;
	
	@Override
	public Perfil findById(Integer clavePk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Perfil entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOne(Perfil entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Integer clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Perfil buscarPorNombre(String nombre) {
		
		return prepo.findByNombre(nombre);
	}

}
