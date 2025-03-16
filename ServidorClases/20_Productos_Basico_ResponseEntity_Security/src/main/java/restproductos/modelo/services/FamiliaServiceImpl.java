package restproductos.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Familia;
import restproductos.repository.FamiliaRepository;
@Service
public class FamiliaServiceImpl implements FamiliaServices{
	
	@Autowired
	private FamiliaRepository familiaRepository;

	@Override
	public Familia alta(Familia familia) {
		try {
			if (familiaRepository.existsById(familia.getCodigo()))
				return null;
			else  
				return familiaRepository.save(familia);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Familia modificar(Familia familia) {
		try {
			if (familiaRepository.existsById(familia.getCodigo()))
				return familiaRepository.save(familia);
			else  
				return null;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(int codigo) {
		try {
			if (familiaRepository.existsById(codigo)) {
				familiaRepository.deleteById(codigo);
				return 1;
			}
			else  
				return 0;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Familia buscarUna(int codigo) {
		// TODO Auto-generated method stub
		return familiaRepository.findById(codigo).orElse(null);
	}

	@Override
	public List<Familia> buscarTodos() {
		// TODO Auto-generated method stub
		return familiaRepository.findAll();
	}

}
