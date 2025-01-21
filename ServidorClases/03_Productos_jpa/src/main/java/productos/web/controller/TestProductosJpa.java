package productos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import productos.web.modelo.entidades.Producto;
import productos.web.modelo.repository.ProductoRepository;

@RestController
public class TestProductosJpa {

	@Autowired 
	private ProductoRepository prepo;
	
	@GetMapping(" /apirest/todos")
	
	public List<Producto> todos(){
		return prepo.findAll();

	}		
}
