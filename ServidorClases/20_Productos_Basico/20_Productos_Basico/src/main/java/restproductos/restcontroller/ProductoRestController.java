package restproductos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
	@GetMapping("/")
	public List<Producto> todos() {
		return pservice.buscarTodos();
	}
	
	@GetMapping("/{codigo}")
	public Producto uno(@PathVariable int codigo) {
		return pservice.buscarUna(codigo);
	}
	
	@PostMapping("/")
	public ProductoDto alta(@RequestBody ProductoDto productoDto) {
		
		Producto producto = new Producto();
		
		producto.setColor(productoDto.getColor());
		producto.setMarca(productoDto.getMarca());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setPrecioUnitario(productoDto.getPrecio());
		producto.setFamilia(fservice.buscarUna(productoDto.getCodigoFamilia()));
		pservice.alta(producto);
	//	productoDto.setCodigo(producto.getCodigo());
		return productoDto;
	}
	
	@PutMapping("/")
	public Producto modificar(@RequestBody Producto producto) {
		return pservice.modificar(producto);
	}
	
	@DeleteMapping("/{codigo}")
	public String eliminar(@PathVariable int codigo) {
		switch(pservice.eliminar(codigo)) {
		case 1:  return "Producto eliminado correctamente";
		case 0:  return "Producto no existe";
		case -1: return "Esto es un problema de la base de datos, llme a servicio Tecnico";
		default:  return null;
		}
	}
	
	
	

}
