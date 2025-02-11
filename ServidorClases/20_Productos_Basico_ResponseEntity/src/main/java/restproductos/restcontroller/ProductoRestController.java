package restproductos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproductos.dto.ProductoDto;
import restproductos.modelo.entities.Familia;
import restproductos.modelo.entities.Producto;
import restproductos.modelo.services.FamiliaServices;
import restproductos.modelo.services.ProductoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoService pservice;
	
	@Autowired
	private FamiliaServices fservice;
	
	@GetMapping("/media/{codigoFamilia}")
	public ResponseEntity<?>  media(@PathVariable int codigoFamilia) {
		return new ResponseEntity<>(pservice.mediaPrecioProdPorFamilia(codigoFamilia), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Producto>>  todos() {
		return new ResponseEntity<List<Producto>>(pservice.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> uno(@PathVariable int codigo) {
		 Producto producto = pservice.buscarUna(codigo);
		 if ( producto != null)
			 return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		 else
			 return new ResponseEntity<String>("Producto no existe", HttpStatus.NOT_FOUND);
			 
	}
	
	@PostMapping("/")
	public ResponseEntity<Producto> alta(@RequestBody ProductoDto productoDto) {
		
		Producto producto = productoDto.convertToproducto();
		
		
	 	producto.setFamilia(fservice.buscarUna(productoDto.getCodigoFamilia()));
		
		return 	new ResponseEntity<Producto>(pservice.alta(producto), HttpStatus.CREATED);
		
	//   	productoDto.setCodigo(producto.getCodigo());
		// return productoDto;
	}
	
	@PutMapping("/")
	public Producto modificar(@RequestBody Producto producto) {
		return pservice.modificar(producto);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<String> eliminar(@PathVariable int codigo) {
		switch(pservice.eliminar(codigo)) {
		case 1:  return new ResponseEntity<String>("Producto eliminado correctamente", HttpStatus.OK);
		case 0:  return new ResponseEntity<String>("Producto no existe", HttpStatus.NOT_FOUND);
		case -1: return new ResponseEntity<String> 
			("Esto es un problema de la base de datos, llame a servicio Tecnico",HttpStatus.CONFLICT);
		default:  return null;
		}
	}
	
	
	

}
