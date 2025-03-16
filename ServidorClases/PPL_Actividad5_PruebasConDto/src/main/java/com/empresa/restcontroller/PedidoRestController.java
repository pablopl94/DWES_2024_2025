package com.empresa.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.dto.PedidoDto;
import com.empresa.entidades.Comercial;
import com.empresa.services.ComercialServices;
import com.empresa.services.PedidoServices;

@RestController
@CrossOrigin(origins = "+")
@RequestMapping("/jefecomercial")
public class PedidoRestController {

    @Autowired
    private ComercialServices comercialServices;

    @Autowired
    private PedidoServices pedidoServices;


	//Devolver la lista de pedidos gestionados por el comercial que coincida con ese id. Usar el PedidoDto como salida.
	@GetMapping("/pedidos/{idComercial}")
	public ResponseEntity<?> listaComercialesPorCliente(@PathVariable int idComercial){
		
		Comercial comercial = comercialServices.findById(idComercial);
		
		if(comercial == null) {
			return new ResponseEntity<String>("El comercial " + idComercial + " no existe", HttpStatus.NOT_FOUND);
		}
		
		List<PedidoDto> listaPedidosDto = pedidoServices.buscarPedidosPorComercial(idComercial);
		
		if(!listaPedidosDto.isEmpty()) {
			return new ResponseEntity<>(listaPedidosDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("El comercial no tiene pedidos", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//Obtener la suma de los importes de los pedidos gestionados por cada comercial.  Obtener un Map<String,Double>, con el nombre y apellidos de cada comercial, y el importe total. Busca cómo hacerlo con funciones de Stream.
	@GetMapping("/totalpedidos")
   public ResponseEntity<Map<String, Double>> obtenerImporteTotalPorComercial() {
       Map<String, Double> importesComerciales = pedidoServices.obtenerImporteTotalComerciales();
       return new ResponseEntity<Map<String,Double>>(importesComerciales, HttpStatus.OK);
   }	

	
}
