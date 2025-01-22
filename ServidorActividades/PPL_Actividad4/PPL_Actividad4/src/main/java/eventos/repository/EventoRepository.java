package eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.entidades.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    
	 // Lista de eventos por estado
    List<Evento> findByEstado(String estado);

    //Lista de eventos destacados
    List<Evento> findByDestacado(String destacado);
    
    //Lista de eventos por IdEvento
    List<Evento> findByIdEvento(int idEvento);

    // Consultar eventos por tipo
    @Query("SELECT e FROM Evento e WHERE e.tipo.idTipo = ?1")
    List<Evento> findByIdTipo(int idTipo);

}
