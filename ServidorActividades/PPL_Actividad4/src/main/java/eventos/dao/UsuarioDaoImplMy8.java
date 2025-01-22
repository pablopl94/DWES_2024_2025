package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Reserva;
import eventos.entidades.Usuario;
import eventos.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao{

	@Autowired
	private UsuarioRepository urepo;


	@Override
	public Usuario findById(String clavePk) {
		return urepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Usuario entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOne(Usuario entidad) {
		try {
			Usuario newUsuario = urepo.save(entidad);
			return 1;
		} catch (Exception e) {
			 e.printStackTrace();
			 return 0;
		}
	}

	@Override
	public int deleteOne(String clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
