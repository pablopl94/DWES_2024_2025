package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Reserva;
import eventos.repository.ReservaRepository;

@Repository
public class ReservaDaoImpListMy8 implements ReservaDao{

	@Autowired
	private ReservaRepository resrepo;
	@Override
	public Reserva findById(Integer clavePk) {
		return resrepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Reserva entidad) {
		try {
			if(resrepo.existsById(entidad.getIdReserva())) {
			   resrepo.save(entidad);
				return 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
			return 0;
	}

	@Override
	public int insertOne(Reserva entidad) {
		try {
			Reserva newReserva = resrepo.save(entidad);
			return 1;
		} catch (Exception e) {
			 e.printStackTrace();
			 return 0;
		}
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if(resrepo.existsById(clavePk) ) {
				resrepo.deleteById(clavePk);
					return 1;
			}
			}catch(Exception e) {
				e.printStackTrace();
		}
				return 0;
	}

	@Override
	public List<Reserva> buscarReservasPorUsuario(String username) {
		return resrepo.findReservaByUsuarioUsername(username);
		
	
	}

	@Override
	public boolean eliminarReservasDeEvento(int idEvento) {
	    try {
	        List<Integer> idsReservas = resrepo.findReservasByEvento(idEvento);
	        
	        if (!idsReservas.isEmpty()) {
	        	
	            resrepo.deleteAllById(idsReservas);
	            
	            return true; 
	        }
	        return false; 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        return false;
	    }
	}
	
	
    public int calcularAforoDisponible(int idEvento, int aforoMaximo) {
        // Obtener todas las reservas del evento
        List<Reserva> reservas = resrepo.listReservasByEvento(idEvento);

        // Sumar la cantidad de asistentes en memoria
        int totalAsistentes = reservas.stream().mapToInt(Reserva::getCantidad).sum();

        // Calcular aforo disponible evitando valores negativos
        int aforoDisponible = Math.max(aforoMaximo - totalAsistentes, 0);

        // LOG para verificar si el cálculo es correcto
        System.out.println("Evento ID: " + idEvento);
        System.out.println("Aforo Máximo: " + aforoMaximo);
        System.out.println("Total Asistentes Reservados: " + totalAsistentes);
        System.out.println("Aforo Disponible: " + aforoDisponible);

        return aforoDisponible;
    }

}
