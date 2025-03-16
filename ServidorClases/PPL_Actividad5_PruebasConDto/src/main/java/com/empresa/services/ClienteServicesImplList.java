package com.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entidades.Cliente;
import com.empresa.repository.ClienteRepository;

@Service
public class ClienteServicesImplList implements ClienteServices{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente findById(Integer clavePk) {
		return clienteRepository.findById(clavePk).orElse(null);
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente insertOne(Cliente entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente updateOne(Cliente entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteOne(Integer clavePk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
