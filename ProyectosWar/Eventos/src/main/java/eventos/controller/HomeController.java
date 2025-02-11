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

	// MANEJO INICIO SESION Y REDIRIGIR AL HOME
	@GetMapping("/inicioSesion")
	public String inicioSesion(Authentication aut, HttpSession sesion) {

		// Obtiene el nombre de usuario autenticado
		String username = aut.getName();

		// Busca el usuario en la base de datos
		Usuario usuario = udao.findById(username);

		// Por seguridad, eliminamos la contraseña
		usuario.setPassword(null);

		// Guardamos el usuario en la sesion
		sesion.setAttribute("usuario", usuario);

		// Redirige a la página de inicio
		return "forward:/home";
	}

	// MUESTRA EL FORMULARIO DEL LOGIN
	@GetMapping("/login")
	public String Login() {

		return "formLogin";
	}

	// MANEJA
	@GetMapping("/login-error")
	public String Login(Model model) {

		model.addAttribute("error", "Usuario o contraseña erronea");

		return "formLogin";
	}

	// PAGINA PRINCIPAL DE LA APP, HOME
	@GetMapping({ "/home" })
	public String home(HttpSession sesion, Model model) {
		// Obtenemos y cargamos la lista de eventos destacados y aceptados al modelo
		model.addAttribute("eventosDestacados", edao.buscarPorDestacados());
		model.addAttribute("eventosAceptados", edao.buscarPorAceptados());

		return "Home";
	}

	// MUESTRA EL FORMULARIO DE LA VISTA QUE HEMOS CREADO
	@GetMapping({ "/registro" })
	public String registro(Model model) {
		// Creo un objeto usuario nuevo y vacio para que el formulario lo rellene
		model.addAttribute("usuario", new Usuario());

		return "formRegistro";
	}

	// REGITRAR USUARIO
	@PostMapping({ "/registro" })
	public String altaUsuario(RedirectAttributes rat, Perfil perfil, Usuario usuario) {

		// Verificar que el username se asigna correctamente
		if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
			rat.addFlashAttribute("error", "El campo username es obligatorio.");
			return "redirect:/registro";
		}

		// Busca el perfil de cliente para asignarlo al usuario que se crea
		// es decir todos lo usuarios que se registren tendra por defecto
		// el rol de cliente
		Perfil perfilCliente = pdao.buscarPorNombre("ROLE_CLIENTE");

		// Establece la fecha de registro, activa el usuario por defecto
		// le asigna el perfil y añadimos el noop a la contraseña para que funcione
		usuario.setFechaRegistro(new Date());
		usuario.setEnabled(1);
		usuario.setPerfiles(List.of(perfilCliente));
		usuario.setPassword("{noop}" + usuario.getPassword());

		// Añadimos el usuario a la bbdd y mandamos mensaje de exito o error en cada
		// caso
		if (udao.insertOne(usuario) == 1) {
			rat.addFlashAttribute("mensaje", "Usuario dado de alta correctamente");
		} else {
			rat.addFlashAttribute("error", "El usuario no se  ha podido dar de alta");
			return "redirect:/registro";
		}
		return "redirect:/login";

	}

}
