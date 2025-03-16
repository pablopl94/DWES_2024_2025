package proyectos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Perfil;
import proyectos.modelo.repository.PerfilRepository;

@Service
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilRepository prepo;

	@Override
	public Perfil findById(Integer clavePk) {
		return prepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Perfil> findAll() {
		return prepo.findAll();
	}

	@Override
	public Perfil insertOne(Perfil entidad) {
		try {
			if(prepo.existsById(entidad.getIdPerfil())) {
				return null;
			}else {
				return prepo.save(entidad);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Perfil updateOne(Perfil entidad) {
		try {
			if(prepo.existsById(entidad.getIdPerfil())) {
				return prepo.save(entidad);
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if(prepo.existsById(clavePk)) {
				prepo.deleteById(clavePk);
				return 1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
