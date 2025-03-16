package proyectos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Departamento;
import proyectos.modelo.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	private DepartamentoRepository drepo;
	
	@Override
	public Departamento findById(Integer clavePk) {
		return drepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Departamento> findAll() {
		return drepo.findAll();
	}

	@Override
	public Departamento insertOne(Departamento entidad) {
		try {
			if(drepo.existsById(entidad.getIdDepar())) {
				return null;
			}else {
				return drepo.save(entidad);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Departamento updateOne(Departamento entidad) {
		try {
			if(drepo.existsById(entidad.getIdDepar())) {
				return drepo.save(entidad);
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if(drepo.existsById(clavePk)) {
				drepo.deleteById(clavePk);
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
