package eventos.dao;

import java.util.List;



import eventos.entidades.Evento;
;

public interface EventoDao extends IGenericoCrud<Evento,Integer> {
	

	 // Lista de eventos por estado
    List<Evento> buscarPorTerminados();
    List<Evento> buscarPorCancelados();
    List<Evento> buscarPorAceptados();

    // Lista de eventos destacados
    List<Evento> buscarPorDestacados();
    
    // Buscar eventos destacados
    List<Evento> listaDeDetalles(int idEvento);

    // Consultar eventos por tipo
    List<Evento> buscarPorTipo(int idTipo);
    

    
}
