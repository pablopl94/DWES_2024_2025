package eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.entidades.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	

	
	@Query("SELECT r.idReserva FROM Reserva r WHERE r.evento.idEvento =?1  " )
	List<Integer> findReservasByEvento (int idEvento);
	
	@Query("SELECT r FROM Reserva r WHERE r.usuario.username =?1 ")
	List<Reserva> findReservaByUsuarioUsername (String username);
	
	@Query("SELECT r FROM Reserva r WHERE r.evento.idEvento =?1 ")
	List<Reserva> listReservasByEvento(int idEvento);
	
  
}
