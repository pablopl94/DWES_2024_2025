package proyectos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Cliente;
import proyectos.modelo.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository crepo;

	@Override
	public Cliente findById(String clavePk) {
		return crepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Cliente> findAll() {
		return crepo.findAll();
	}

	@Override
	public Cliente insertOne(Cliente entidad) {
		try {
			if(crepo.existsById(entidad.getCif())) {
				return null;
			}else {
				return crepo.save(entidad);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Cliente updateOne(Cliente entidad) {
		try {
			if(crepo.existsById(entidad.getCif())) {
				return crepo.save(entidad);
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(String clavePk) {
		try {
			if(crepo.existsById(clavePk)) {
				crepo.deleteById(clavePk);
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
