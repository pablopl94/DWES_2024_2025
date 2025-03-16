package proyectos.modelo.service;


import proyectos.modelo.entity.Cliente;
import proyectos.modelo.entity.Proyecto;

public interface ProyectoService extends IGenericCrud<Proyecto, String> {

	Cliente listaClientesPorProyecto (String idProyecto);
}
