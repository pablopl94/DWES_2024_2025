package familias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import familias.modelo.dao.UsuarioDao;
import familias.modelo.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {
	
	@Autowired
	private UsuarioDao udao;
	
	@GetMapping({"","/","/home"})
	public String inicio() {
		return "home";
	}
	
	@GetMapping("/home2")
	public String inicio2() {
		return "home2";
	}
	
	@GetMapping("/login")
	public String mostrarFormLogin() {
		// necesita este formulario
		// datos dinamicos?
		return "FormLogin";
	}
	@PostMapping("/login")
	public String procesarFormLogin(@RequestParam String username,
			@RequestParam String password, HttpSession sesion,
			RedirectAttributes ratt) {
		
		Usuario usuario = udao.login(username, password);
		
		if (usuario != null) {
			//usuario.setPassword(null);
			sesion.setAttribute("usuario", usuario);
			ratt.addFlashAttribute("usuario2", usuario.getNombre());
			return "redirect:/";
			
		}else {
			ratt.addFlashAttribute("mensaje", "usuario o password incorrecto");
			return "redirect:/login";
			
		}
			
		
		
		
	}

	@GetMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("usuario");
		sesion.invalidate();
		return "forward:/";
	}
	
	
	@GetMapping("/registro")
	public String mostrarFormRegistro() {
		return "noSeComo";
	}
	@PostMapping("/registro")
	public String procesarFormRegistro() {
		return "noSeComo";
	}
}
