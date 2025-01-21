package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import eventos.dao.EventoDao;
import eventos.dao.PerfilDao;
import eventos.dao.UsuarioDao;
import eventos.entidades.Perfil;
import eventos.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioDao udao;
	
	@Autowired
	private EventoDao edao;
	
	@Autowired
	private PerfilDao pdao;
	
	@GetMapping("/inicioSesion")
	public String inicioSesion(Authentication aut, HttpSession sesion) {
		
	    String username = aut.getName();
	    Usuario usuario = udao.findById(username);
	    usuario.setPassword(null); 
	    
	    sesion.setAttribute("usuario", usuario);
	    
	    return "forward:/home";
	}

	@GetMapping("/login")
	public String Login() {
		
	    return "formLogin";     
	}
	
	@GetMapping("/login-error")
	public String Login(Model model) {
		
		model.addAttribute("error", "Usuario o contraseña erronea");
		
	    return "formLogin"; 
	}

	@GetMapping({"/home"})
	public String home(HttpSession sesion, Model model) {

	    model.addAttribute("eventosDestacados", edao.buscarPorDestacados());
	    model.addAttribute("eventosAceptados", edao.buscarPorAceptados());

	    return "home"; 
	}
	
	@GetMapping({"/registro"})
	public String registro(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
	    return "formRegistro"; 
	}
	
	@PostMapping({"/registro"})
	public String altaUsuario( RedirectAttributes rat, Perfil perfil, Usuario usuario) {
		
		// Verificar que el username se asigna correctamente
	    if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
	        rat.addFlashAttribute("error", "El campo username es obligatorio.");
	        return "redirect:/registro";
	    }
		
		Perfil perfilCliente = pdao.buscarPorNombre("ROLE_CLIENTE"); 

		usuario.setFechaRegistro(new Date());
		usuario.setEnabled(1);
		usuario.setPerfiles(List.of(perfilCliente));
		usuario.setPassword("{noop}" + usuario.getPassword());
		
		if(udao.insertOne(usuario) == 1) {
			rat.addFlashAttribute("mensaje", "Usuario dado de alta correctamente");
		}else {
			rat.addFlashAttribute("error", "El usuario no se  ha podido dar de alta");
			return "redirect:/registro";
		}
	    return "redirect:/login";
		
	}
	
	


}
