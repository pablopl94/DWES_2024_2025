package proyectos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectos.modelo.entity.Cliente;
import proyectos.modelo.service.ClienteService;
import proyectos.modelo.service.ProyectoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@GetMapping("/todos")
	public List<Cliente> getMethodName() {
		return clienteService.findAll();
	}
	
	
	@GetMapping("/bycliente/{cif}")
	public ResponseEntity<?> buscarUno(@PathVariable String cif ) {
		
		Cliente cliente = clienteService.findById(cif);
		
		if(cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Cliente no existe", HttpStatus.NOT_FOUND);
	}

	//Clientes de un pedido
	@GetMapping("/byproyecto/{idProyecto}")
	public ResponseEntity<?> listaClientesDeUnProyecto (@PathVariable String idProyecto) {
		
		Cliente  cliente = proyectoService.listaClientesPorProyecto(idProyecto);
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	

}
