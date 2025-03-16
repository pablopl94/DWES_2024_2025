package proyectos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.ProyectoConEmpleados;
import proyectos.modelo.repository.ProyectoConEmpleadosRepository;


@Service
public class ProyectoConEmpleadosServiceImpl implements ProyectoConEmpleadosService{
	
	@Autowired
	private ProyectoConEmpleadosRepository pcerepo;

	@Override
	public List<ProyectoConEmpleados> buscarProyectoConEmpleadoPorProyecto(String idProyecto) {
		return pcerepo.findByProyecto_IdProyecto(idProyecto);
	}



}

	
