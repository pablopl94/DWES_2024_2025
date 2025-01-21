package familias.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import familias.modelo.dao.FamiliaDao;
import familias.modelo.entidades.Familia;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/familias")
public class FamiliaRestController {
	
	@Autowired
	private FamiliaDao fdao;

	@GetMapping("/")
	public List<Familia> todas() {
		return fdao.buscarTodos();
	}

//	@GetMapping("/{idFamilia}")
	
//	@PostMapping("/")
	
//	@PutMapping("/")
	
//	@DeleteMapping("/{idFamilia}")
}



