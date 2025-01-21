package familias.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import familias.modelo.entidades.Usuario;
import familias.repository.UsuarioRepository;
@Repository
public class UsuarioImplJpaMy8 implements UsuarioDao{
	
	@Autowired
	private UsuarioRepository urepo;

	@Override
	public Usuario login(String username, String password) {
		return urepo.findByUsernameAndPassword(username, password);
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		try {
			return urepo.save(usuario);// ESTO ES VALIDAD CUANDO LA ID SE AUTOINCREMENTA++
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	}


