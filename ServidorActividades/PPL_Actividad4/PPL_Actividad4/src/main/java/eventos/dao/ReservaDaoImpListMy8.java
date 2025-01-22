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
		return resrepo.findReservaByUsarioUsername(username);
		
	
	}

	@Override
	public boolean eliminarReservasDeEvento(int idEvento) {
	    try {
	        List<Integer> idsReservas = resrepo.obtenerIdsReservasPorEvento(idEvento);
	        
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



}
