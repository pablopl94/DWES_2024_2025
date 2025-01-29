package restproductos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproductos.entidades.Familia;
import restproductos.services.FamiliaServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "+")
@RequestMapping("/familias")
public class FamiliaRestController {
	
	@Autowired 
	private FamiliaServices familiaServices;
	
	@GetMapping("/")
	public List <Familia> buscarTodas() {
		return familiaServices.findAll();	
	}
	
	
	@GetMapping("/{codigo}")
	public Familia buscarUna(@PathVariable int codigo) {
		return familiaServices.findById(codigo);	
	}
	
	
	@PostMapping("/")
	public Familia altaFamilia(@RequestBody Familia familia ) {
		return familiaServices.insertOne(familia);
	}
	
	@PutMapping("/")
	public Familia modificarFamilia(@RequestBody Familia familia ) {
		return familiaServices.updateOne(familia);
	}
	
	@DeleteMapping("/{codigo}")
	public String eliminarFamilia(@PathVariable int codigo ) {
		switch (familiaServices.deleteOne(codigo)) {
		case 1: return "Familia eliminada correctamente";
		case 0: return "Familia no existe";
		case-1: return "Esta Familia tiene Productos y no se puede eliminar";
		default: return null;
		}

	}
		
}
	
	


