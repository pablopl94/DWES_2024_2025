package proyectos.modelo.service;

import java.util.List;

import proyectos.modelo.entity.ProyectoConEmpleados;

public interface ProyectoConEmpleadosService{

	 List<ProyectoConEmpleados> buscarProyectoConEmpleadoPorProyecto (String idProyecto);
}
