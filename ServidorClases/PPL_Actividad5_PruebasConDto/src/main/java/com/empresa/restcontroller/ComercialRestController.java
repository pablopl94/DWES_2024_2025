package com.empresa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entidades.Cliente;
import com.empresa.entidades.Comercial;
import com.empresa.services.ClienteServices;
import com.empresa.services.ComercialServices;
import com.empresa.services.PedidoServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jefecomercial")
public class ComercialRestController {

	@Autowired
	private ComercialServices comercialServices;
	
	@Autowired
	private PedidoServices pedidoServices;
	
	@Autowired
	private ClienteServices clienteServices;
	
	
	//Alta comercial
	@PostMapping("/alta")
	public ResponseEntity<?> altaComercial(@RequestBody Comercial comercial){
		//Damos de alta el nuevo comercial y lo guardamos en un objeto
		Comercial nuevoComercial = comercialServices.insertOne(comercial);
		
		//Compruebo si el comercial se ha insertado correctamente
		 if( nuevoComercial != null) {
			 //Si esta bien, le mandamos el comercial añadido
			 return new ResponseEntity<Comercial>(nuevoComercial, HttpStatus.CREATED);
		 } else {
			 //Si hay algun error, le mandamos un estado BAD_REQUEST con un mensaje de error
			 return new ResponseEntity<String>("Error: El idCodigo de comercial ya existe", HttpStatus.BAD_REQUEST);
		 }
	}
	
	//Eliminar de la bbdd el comercial cuyo id coincida, siempre y cuando no tenga pedidos a su cargo. Informar a la salida.
	@DeleteMapping("/eliminar/{idComercial}")
	public ResponseEntity<?> eliminarComercial(@PathVariable int idComercial){
		
		//Creo un nuevo objeto en el que se guarda el comercial que se busca por su ID, 
		//para luego poder sacar su nombre y apellido en los mensajes
		Comercial comercial = comercialServices.findById(idComercial);
		
		switch(comercialServices.deleteOne(idComercial)) {
		case 1: return new ResponseEntity<String>("El comercial "+ comercial.getNombre() +" "+ comercial.getApellido1() +  " ha sido eliminado de la bdd correctamente", HttpStatus.OK);
		case 0: return new ResponseEntity<String>("El comercial no existe en la bdd", HttpStatus.NOT_FOUND);
		case -1: return new ResponseEntity<String>("El comercial tiene pedidos, no se puede eliminar", HttpStatus.BAD_REQUEST);
		default : return null;
		}
	}
	
	//Devolver los datos del comercial cuyo id coincida
	@GetMapping("/uno/{idComercial}")
	public ResponseEntity<?> buscarUnComercial(@PathVariable int idComercial){
		
		//Buscamos la id de comercial
		Comercial comercial = comercialServices.findById(idComercial);
		
		//Comprobamos si la idComercial existe, si existe mandamos la lista con status OK, sino mandamos NOT_FOUND 
		if(comercial != null) {
			return new ResponseEntity<Comercial>(comercial, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>("El idComercial no existe", HttpStatus.NOT_FOUND);
		}
		
	}
	
	//Devolver la lista de comerciales que no  han atendido ningún  pedido
	@GetMapping("/sinpedidos")
	public ResponseEntity<?> listaComercialesSinPedidos(){
		
		List<Comercial> listaComerciales = pedidoServices.comercialesSinPedidos();
		
		if(!listaComerciales.isEmpty()) {
			return new ResponseEntity<List<Comercial>>(listaComerciales, HttpStatus.OK);
		}else {
			return  new ResponseEntity<String>("No se han encontrado comerciales sin pedidos",HttpStatus.NOT_FOUND);
		}
	}
	
	//Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.
	@GetMapping("/bycliente/{idCliente}")
	public ResponseEntity<?> ComercialesDeCliente (@PathVariable int idCliente){
		
		Cliente cliente = clienteServices.findById(idCliente);
		
		if(cliente == null) {
			return new ResponseEntity<String>("El id: " + idCliente + " no existe", HttpStatus.NOT_FOUND );
		}
		
		List<Comercial> listaComerciales = pedidoServices.buscarComercialesPorPedidoCliente(idCliente);
		
		if(!listaComerciales.isEmpty()) {
			return new ResponseEntity<List<Comercial>>(listaComerciales, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Este cliente no tiene pedidos", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
