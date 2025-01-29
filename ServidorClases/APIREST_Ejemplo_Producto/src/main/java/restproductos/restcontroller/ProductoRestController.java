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
import restproductos.entidades.Familia;
import restproductos.entidades.Producto;

import restproductos.services.FamiliaServices;
import restproductos.services.ProductoServices;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoServices productoService;
	
	@Autowired
	private FamiliaServices familiaServices;
	

	
	// BUSCAR UN PRODUCTO
	@GetMapping("/{codigo}")
	public Producto oneProduct(@PathVariable int codigo){
		return productoService.findById(codigo);
	}
	
	//LISTAS DE PRODUCTOS
	@GetMapping("/")
	public List<Producto> allProducts(){
		return productoService.findAll();
	}
	
	/*@GetMapping("/")
	public List<Producto> allProductsByFamily(@PathVariable int codigo){
		return productoService.findByFamilia(codigo);
	}
	*/

	
	//ALTA , EDITAR Y ELIMINAR
	@PostMapping("/")
	public Producto addProduct(@RequestBody ProductoDto productoDto) {

	   // Convertir el ProductoDto a Producto usando el mapper
	   Producto producto = productoDto.toDto();
	    
	   producto.setFamilia(familiaServices.findById(productoDto.getCodigoFamilia()));

	    return productoService.insertOne(producto);
	   
	   // Actualizar el DTO con el ID generado y devolverlo
	   //productoDto.setCodigo(producto.getCodigo());
	   
	  // return producto;
	   
	   
	}

	
	@PutMapping("/")
	public Producto editProduct(@RequestBody Producto producto) {
		return productoService.updateOne(producto);
	}
	
	@DeleteMapping("/{codigo}")
	public String deleteProduct(@PathVariable int codigo) {
		switch(productoService.deleteOne(codigo)) {
		case 1: return "Producto eliminado correctamente";
		case 0: return "El producto no existe";
		case -1: return "Problema en la base de datos";
		default: return null;
		}
	}
	
	
	
	
	

}
