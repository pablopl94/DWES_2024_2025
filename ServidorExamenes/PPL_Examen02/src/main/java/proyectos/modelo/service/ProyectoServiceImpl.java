package proyectos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Cliente;
import proyectos.modelo.entity.Proyecto;
import proyectos.modelo.repository.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	
	@Autowired
	private ProyectoRepository prepo;
	@Override
	public Proyecto findById(String clavePk) {
		return prepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Proyecto> findAll() {
		return prepo.findAll();
	}

	@Override
	public Proyecto insertOne(Proyecto entidad) {
		try {
			if(prepo.existsById(entidad.getIdProyecto())) {
				return null;
			}else {
				return prepo.save(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Proyecto updateOne(Proyecto entidad) {
		try {
			if(prepo.existsById(entidad.getIdProyecto())) {
				return prepo.save(entidad);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int deleteOne(String clavePk) {
		try {
			if(prepo.existsById(clavePk)) {
				prepo.deleteById(clavePk);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Cliente listaClientesPorProyecto(String idProyecto) {
		Proyecto proyecto = prepo.findById(idProyecto).orElse(null);
		
		return proyecto.getCliente();	
	}

}
