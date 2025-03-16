package restproductos.modelo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Perfil;
import restproductos.repository.PerfilRepository;
@Service
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public Perfil buscarUno(int idPerfil) {
		// TODO Auto-generated method stub
		return perfilRepository.findById(idPerfil).orElse(null);
	}

	@Override
	public Perfil buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return perfilRepository.findAll()
				.stream()
				.filter(p-> p.getNombre().equalsIgnoreCase(nombre))
				.findFirst()
				.orElse(null);

	}

}
