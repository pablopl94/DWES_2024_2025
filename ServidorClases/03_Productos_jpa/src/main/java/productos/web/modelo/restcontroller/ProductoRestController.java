package productos.web.modelo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import productos.web.modelo.dao.ProductoDao;
import productos.web.modelo.entidades.Producto;
import productos.web.modelo.repository.ProductoRepository;

//---->api rest: ruta + funcion de http: get , post , put , delete
//---->funcion: que devuelve en el mensaje de salida http:body >  datos > JSON

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/api/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoDao pdao;
	
	@Autowired
	private ProductoRepository prepo;
	
	@GetMapping("/pordescripcion/{cadena}")
	public List<Producto> porCadena(@PathVariable String cadena){
		
		return pdao.findByDescripcionProducto(cadena);
	}
	
	//METODOS DERIVADOS
	
	// findbyDescripcionContaing
	@GetMapping("/pordescripcion2/{cadena}")
	public List<Producto> porCadenaContaining(@PathVariable String cadena){
		
		return prepo.findByDescripcionContaining(cadena);
	}
	
	// findbyPrecioGreaterThann
		@GetMapping("/preciostock/{precio}/{stock}")
		public List<Producto> porPrecioStock(@PathVariable double precio, @PathVariable int stock){
			
			return prepo.findByPrecioGreaterThanAndStockLessThan(precio , stock);
		}
	
	
	
	
}
