package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import eventos.entidades.Tipo;
import eventos.repository.TipoRepository;

@Repository
public class TipoDaoImplMy8 implements TipoDao{
	
	@Autowired
	private TipoRepository trepo; 
	
	@Override
	public Tipo findById(Integer clavePk) {
		return trepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Tipo> findAll() {
		return trepo.findAll();
	}

	@Override
	public int updateOne(Tipo entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Integer clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOne(Tipo entidad) {
		// TODO Auto-generated method stub
		return 0;
	}




}
