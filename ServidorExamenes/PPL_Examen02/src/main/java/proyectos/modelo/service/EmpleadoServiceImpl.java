package proyectos.modelo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Empleado;
import proyectos.modelo.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository erepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Empleado findById(Integer clavePk) {
		return erepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Empleado> findAll() {
		return erepo.findAll();
	}

	@Override
	public Empleado insertOne(Empleado entidad) {
		try {
			if(erepo.existsById(entidad.getIdEmpl())) {
				return null;
			}else {
				return erepo.save(entidad);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Empleado updateOne(Empleado entidad) {
		try {
			if(erepo.existsById(entidad.getIdEmpl())) {
				return erepo.save(entidad);
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
			if(erepo.existsById(clavePk)) {
				erepo.deleteById(clavePk);
				return 1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			return -1;
		}
	}

}
