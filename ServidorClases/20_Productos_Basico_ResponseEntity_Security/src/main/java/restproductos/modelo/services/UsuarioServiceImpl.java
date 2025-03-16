package restproductos.modelo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Usuario;
import restproductos.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public Usuario buscarUno(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(username).orElse(null);
	}

	@Override
	public Usuario buscarEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
