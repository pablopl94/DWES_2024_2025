package restproductos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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
	public ResponseEntity <?> buscarProducto (@PathVariable int codigo){
		Producto producto =  productoService.findById(codigo);
		
		if(producto != null) {
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		} else {
			 return new ResponseEntity<String>("El producto no existe", HttpStatus.NOT_FOUND);
		}
		
	}
	
	//LISTAS DE PRODUCTOS
	@GetMapping("/")
	public ResponseEntity<List<Producto>> listaTodos(){
		return new ResponseEntity <List<Producto>>(productoService.findAll(),HttpStatus.OK);
	}
	
	//FILTRO MARCA Y COLOR
	@GetMapping("/filtrar")
	public ResponseEntity<?> filterByBrandOrColor (@RequestParam(required = false) String marca, @RequestParam (required = false)String color){
		

	    // Obtener la lista de productos filtrados
	    List<Producto> productos = productoService.findByBrandAndColor(marca, color);

	    // Si la lista está vacía, devolver error
	    if (productos.isEmpty()) {
	        return new ResponseEntity<>("No se encontraron productos con los filtros especificados", HttpStatus.NOT_FOUND);
	    }

	    // Si hay productos, devolver la lista
	    return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	
	//MEDIA PRECIO PRODUCTOS POR FAMILIA
	@GetMapping("/media/{codigoFamilia}")
	public ResponseEntity<?> mediaProductosByFamilia(@PathVariable int codigoFamilia){
		
		Familia familia = familiaServices.findById(codigoFamilia);
		
		if(familia == null){
			return new ResponseEntity<String>("La familia no existe", HttpStatus.NOT_FOUND);
		} 
		
		double media = productoService.findByAverageProdPriceByFamily(codigoFamilia);
		
		if(media <= 0) {
			return new ResponseEntity<String>("No hay productos en esta familia", HttpStatus.NO_CONTENT);
		} 
	
			return new ResponseEntity<>(media, HttpStatus.OK);
		
	}

	
	//ALTA PRODUCTO
	@PostMapping("/alta")
	public ResponseEntity<?> altaProduct(@RequestBody @Valid ProductoDto productoDto, BindingResult resultado) {
		
		
			
		Familia familia = familiaServices.findById(productoDto.getCodigoFamilia());
		
		if (familia == null) {
			return new ResponseEntity<String>("Familia no encontrada", HttpStatus.NOT_FOUND);
		}
	
		//SACAR LOS ERRORES DE LAS VALIDACIONES CON BINDINGRESULT
		if(resultado.hasErrors()) {
			List<String> lista = 
					resultado.getAllErrors().stream()
						.map(error -> error.getDefaultMessage())
						.toList();
					return new ResponseEntity<>(lista,HttpStatus.BAD_REQUEST);		
		}
		
		Producto producto = productoDto.toDto();
		producto.setFamilia(familia);	
	   
		Producto newProducto = productoService.insertOne(producto);
		
		return new ResponseEntity<Producto>(newProducto, HttpStatus.CREATED);
	}

	//EDITAR PRODUCTO
	@PutMapping("/")
	public Producto editProduct(@RequestBody Producto producto) {
		return productoService.updateOne(producto);
	}
	
	//ELIMINAR PRODUCTO
	@DeleteMapping("/{codigo}")
	public ResponseEntity<String> deleteProduct(@PathVariable int codigo) {
		switch(productoService.deleteOne(codigo)) {
		case 1: return new ResponseEntity<String>("Producto eliminado correctamente", HttpStatus.OK);
		case 0: return new ResponseEntity<String>("Producto no existe", HttpStatus.NOT_FOUND);
		case -1: return new ResponseEntity<String>("Conflicto al eliminar el producto, contactar con el serivicio Tenico", HttpStatus.CONFLICT);
		default: return null;
		}
	}
	
	
	
	
	

}
