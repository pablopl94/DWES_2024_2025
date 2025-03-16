package com.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidades.Comercial;
import com.empresa.repository.ComercialRepository;

@Service
public class ComercialServiceImpl implements ComercialServices{

	@Autowired
	private ComercialRepository comercialRepository;
	
	@Override
	public Comercial findById(Integer clavePk) {
		return comercialRepository.findById(clavePk).orElse(null);
	}

	@Override
	public List<Comercial> findAll() {
		return comercialRepository.findAll();
	}

	@Override
	public Comercial insertOne(Comercial entidad) {
		try {
			if(comercialRepository.existsById(entidad.getIdComercial())) {
				return null;
			}else {
				return comercialRepository.save(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Comercial updateOne(Comercial entidad) {
		try {
			if(comercialRepository.existsById(entidad.getIdComercial())) {
				return comercialRepository.save(entidad);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if(comercialRepository.existsById(clavePk)) {
				comercialRepository.deleteById(clavePk);
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
