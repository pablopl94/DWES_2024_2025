package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Evento;
import eventos.repository.EventoRepository;

@Repository
public class EventoDaoImplListMy8 implements EventoDao {

	@Autowired
	private EventoRepository erepo;

	@Override
	public Evento findById(Integer clavePk) {
		return erepo.findById(clavePk).orElse(null);
	}

	@Override
	public List<Evento> findAll() {
		return erepo.findAll();
	}

	
	@Override
	public int updateOne(Evento entidad) {
		try {
			if (erepo.existsById(entidad.getIdEvento())) {
				erepo.save(entidad);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	@Override
	public int insertOne(Evento entidad) {
		try {
			Evento newEvento = erepo.save(entidad);
			 return 1;
			 
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if (erepo.existsById(clavePk)) {
				erepo.deleteById(clavePk);
				return 1;
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
	}
	

	@Override
	public List<Evento> buscarPorTerminados() {
		return erepo.findByEstado("TERMINADO");
	}

	@Override
	public List<Evento> buscarPorCancelados() {
		return erepo.findByEstado("CANCELADO");
	}

	@Override
	public List<Evento> buscarPorAceptados() {
		return erepo.findByEstado("ACEPTADO");
	}

	@Override
	public List<Evento> buscarPorDestacados() {
		return erepo.findByDestacado("S");
	}

	@Override
	public List<Evento> buscarPorTipo(int idTipo) {
		return erepo.findByIdTipo(idTipo);
	}

	@Override
	public List<Evento> listaDeDetalles(int idEvento) {
		return erepo.findByIdEvento(idEvento);
	}

}
