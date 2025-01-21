package familias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import familias.modelo.dao.UsuarioDao;
import familias.modelo.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UsuarioDao udao;

	@GetMapping({ "", "/", "/home" })
	public String inicio() {
		return "home";
	}

	@GetMapping("/login")
	public String mostarFormLogin() {
		return "FormLogin";
	}

	@PostMapping("/login")
	public String procesarFormLogin(@RequestParam String username, @RequestParam String password, HttpSession sesion) {

		Usuario usuario = udao.login(username, password);

		if (usuario != null) {
			//usuario.setPassword(null);
			sesion.setAttribute("usuario", usuario);

		}

		return "redirect:/";
	}
	

	@GetMapping("/logout")
    public String cerrarSesion(HttpSession sesion) {
    	sesion.removeAttribute("cuenta");
        sesion.invalidate();
        return "fordward:/";
    }

	@GetMapping("/registro")
	public String mostarFormRegistro() {
		return "noSeComo";
	}

	@PostMapping("/registro")
	public String procesarFormRegistro() {
		return "noSeComo";
	}

}
