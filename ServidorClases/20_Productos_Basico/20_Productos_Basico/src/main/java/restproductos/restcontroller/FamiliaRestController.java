package restproductos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import restproductos.modelo.entities.Familia;
import restproductos.modelo.services.FamiliaServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/familias")
public class FamiliaRestController {
	
	@Autowired
	private FamiliaServices familiaServices;
	
	
	@GetMapping("/")
	public List<Familia> todas() {
		return familiaServices.buscarTodos();
	}
	
	@GetMapping("/{codigo}")
	public Familia una(@PathVariable int codigo) {
		return familiaServices.buscarUna(codigo);
	}
	
	@PostMapping("/")
	public Familia alta(@RequestBody Familia familia) {
		return familiaServices.alta(familia);
	}
	
	@PutMapping("/")
	public Familia modificar(@RequestBody Familia familia) {
		return familiaServices.modificar(familia);
	}
	
	@DeleteMapping("/{codigo}")
	public String eliminar(@PathVariable int codigo) {
		switch(familiaServices.eliminar(codigo)) {
		case 1:  return "Familia eliminada correctamente";
		case 0:  return "Familia no existe";
		case -1: return "Esta Familia tiene Productos y no se puede eliminar";
		default:  return null;
		}
	}
	
	
	

}
