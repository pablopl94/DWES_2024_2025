package restproductos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.entidades.Familia;
import restproductos.repository.FamiliaRepository;

@Service
public class FamiliaServicesImpl implements FamiliaServices{
	
	@Autowired
	private FamiliaRepository familiaRepository;

	@Override
	public Familia findById(Integer ClavePk) {
		return familiaRepository.findById(ClavePk).orElse(null);
	}

	@Override
	public List<Familia> findAll() {
		return familiaRepository.findAll();
	}

	@Override
	public Familia updateOne(Familia entidad) {
		try {
			if(familiaRepository.existsById(entidad.getCodigo())){
				return familiaRepository.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Familia insertOne(Familia entidad) {
		try {
			if(familiaRepository.existsById(entidad.getCodigo()))
				return null;
			else {
				return familiaRepository.save(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
				return null;
		}
	}

	@Override
	public int deleteOne(Integer ClavePk) {
		try {
			if(familiaRepository.existsById(ClavePk)){
				familiaRepository.deleteById(ClavePk);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
				return -1;
		}
	}



}
