package restproductos.restcontroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import restproductos.modelo.entities.Usuario;
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
	
	
	
	@GetMapping("/cud/all")
	public ResponseEntity<List<Producto>> todos(Authentication aut, Principal miUsuario) {
		if (aut != null) {
			System.out.println(aut.getName());
			System.out.println(((Usuario)aut.getPrincipal()).getApellidos());
			System.out.println(miUsuario);
			
		}
		return new ResponseEntity<List<Producto>>(pservice.buscarTodos(), HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/detail/{codigo}")
	public ResponseEntity<?> uno(@PathVariable int codigo) {
		Producto producto = pservice.buscarUna(codigo);
		if (producto != null)
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Este producto no existe", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/cud/insert")
//	public ProductoDto alta(@RequestBody ProductoDto productoDto) {
	public ResponseEntity<?> alta(@RequestBody ProductoDto productoDto) {
			
		 
		/*
		producto.setColor(productoDto.getColor());
		producto.setMarca(productoDto.getMarca());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setPrecioUnitario(productoDto.getPrecio());
		producto.setFamilia(fservice.buscarUna(productoDto.getCodigoFamilia()));
		*/
		Producto producto = productoDto.converToproducto();
		pservice.alta(producto);
		producto.setFamilia(fservice.buscarUna(productoDto.getCodigoFamilia()));
	 	productoDto.setCodigo(producto.getCodigo());
	 	producto = pservice.alta(producto);
		if (producto != null)
			return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Alta no realizada", HttpStatus.FOUND);
	}
	
	@PutMapping("/cud/update")
	public Producto modificar(@RequestBody Producto producto) {
		return pservice.modificar(producto);
	}
	
	@DeleteMapping("/cud/delete/{codigo}")
	public ResponseEntity<?> eliminar(@PathVariable int codigo) {
		switch(pservice.eliminar(codigo)) {
		case 1:  return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.OK);
		case 0:  return new ResponseEntity<>("Producto no existe", HttpStatus.NOT_FOUND);
		case -1: return new ResponseEntity<>("Esto es un problema de la base de datos, llame a servicio Tecnico",
																			HttpStatus.BAD_REQUEST);
		default:  return null;
		}
	}
	
	
	

}
