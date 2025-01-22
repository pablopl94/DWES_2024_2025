package eventos.dao;

import java.util.List;

import eventos.entidades.Reserva;

public interface ReservaDao extends IGenericoCrud<Reserva, Integer>{
	
	List<Reserva> buscarReservasPorUsuario(String username);
	
	
	boolean eliminarReservasDeEvento(int idEvento);
	

}
