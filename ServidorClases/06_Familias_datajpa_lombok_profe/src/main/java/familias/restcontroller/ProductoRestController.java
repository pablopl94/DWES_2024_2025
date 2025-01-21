package familias.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import familias.modelo.dao.ProductoDao;
import familias.modelo.entidades.Producto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoDao pdao;
	
	@GetMapping("/{idProducto}")
    public Producto buscarUno(@PathVariable long idProducto){
		return pdao.buscarUno(idProducto);
	}
	
	@GetMapping("/familia/{idFamilia}")
    public List<Producto> buscarPorFamilia(@PathVariable int idFamilia){
		return pdao.buscarPorFamilia(idFamilia);
	}
}
