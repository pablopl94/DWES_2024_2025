package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eventos.dao.UsuarioDao;

@Controller
@RequestMapping("/admin/")
public class UsuarioController {

	@Autowired
	private UsuarioDao udao;
	
	@GetMapping("/listaUsuarios")
	public String listaUsuario(Model model) {
		
		//Busco la lista de todos los usuario y la añado al modelo para mostrarla en la vista
		model.addAttribute("listaUsuarios", udao.findAll());
		
		return"Usuarios/listaUsuarios";
		
	}
}
