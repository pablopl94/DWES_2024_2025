package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.dao.EventoDao;
import eventos.dao.ReservaDao;
import eventos.dao.TipoDao;
import eventos.entidades.Evento;
import eventos.entidades.Reserva;
import eventos.entidades.Tipo;
import jakarta.servlet.http.HttpServletRequest;

@Controller

public class EventoController {

	@Autowired
	private EventoDao edao;

	@Autowired
	private TipoDao tdao;

	@Autowired
	private ReservaDao resdao;

	// ***** RUTAS PÚBLICAS *****

	// LISTA DE EXCURSIONES ACEPTADAS
	@GetMapping("/public/eventos/aceptados")
	public String listaEventosActivos(Model model) {
		
		// Cargo la lista de eventos aceptados y asi sucesivamente
		// en todas los get de las listas.
		model.addAttribute("eventosAceptados", edao.buscarPorAceptados());

		return "Eventos/listaAceptados";
	}

	// LISTA DE EXCURSIONES ACEPTADAS
	@GetMapping("/public/eventos/cancelados")
	public String listaEventosCancelados(Model model) {

		model.addAttribute("eventosCancelados", edao.buscarPorCancelados());

		return "Eventos/listaCancelados";
	}

	// LISTA DE EXCURSIONES ACEPTADAS
	@GetMapping("/public/eventos/terminados")
	public String listaEventosTermiandos(Model model) {

		model.addAttribute("eventosTerminados", edao.buscarPorTerminados());

		return "Eventos/listaTerminados";
	}

	// LISTA DE EXCURSIONES DESTACADOS
	@GetMapping("/public/eventos/destacados")
	public String listaEventosDestacados(Model model) {

		model.addAttribute("eventosDestacados", edao.buscarPorDestacados());

		return "Eventos/listaDestacados";
	}

	// VER DETALLES DEL EVENTO
	@GetMapping("/public/eventos/detalles/{idEvento}")
	public String detallesEvento(@PathVariable int idEvento, Model model) {
		
		// Busco el evento en la base de datos por su ID
		Evento evento = edao.findById(idEvento);
		// Añado el evento al modelo para que este disponible en la vista
		model.addAttribute("evento", evento);
		// Calculo el aforo disponible para este evento y lo envio a la vista para que lo muestre
		model.addAttribute("aforoDisponible", resdao.calcularAforoDisponible(idEvento, evento.getAforoMaximo()));
		// Obtenemos y enviamos la lista de detalles  del evento
		model.addAttribute("listaDetalles", edao.listaDeDetalles(idEvento));

		return "Eventos/detallesEvento";
	}

	// ***** RUTAS NO PUBLICAS (ADMINISTRADOR) *****

	// CANCELAR EVENTO "CAMBIAR ESTADO"
	@GetMapping("/admin/eventos/cancelar/{idEvento}")
	public String cancelarEvento(@PathVariable int idEvento, RedirectAttributes ratt, HttpServletRequest request) {

		// Busco el evento que se va a cancelar en la bbdd por su id
		Evento evento = edao.findById(idEvento);

		// Compruebo si el evento ya esta cancelado
		if (!evento.getEstado().equals("CANCELADO")) {
			// Si no esta cancelado, se cambia su estado a cancelado
			// , guardo los cambios y se manda un mensaje de exito
			evento.setEstado("CANCELADO");
			edao.updateOne(evento);
			ratt.addFlashAttribute("mensaje", "Evento cancelado corretamente");
		} else {
			// Si ya esta cancelado, se manda un mensaje de error
			ratt.addFlashAttribute("error", "No es posible cancelar el eveento");
		}

		// Esto lo uso para redirigir a la misma pagina en la que se encuentra
		// el usuario, ya que cancelar esta en varias vistas y podemos comprobar
		// en la misma vista que se ha eliminado
		// Guardamos la URL de la página anterior para redirigir al usuario
		String referer = request.getHeader("Referer");
		// Si no hay una pagina de referencia, redirigo a la lista de eventos
		return "redirect:" + (referer != null ? referer : "/eventos");
	}

	// ELIMINAR EVENTO Y SUS RESERVAS ( SI TIENE)
	@GetMapping("/admin/eventos/eliminar/{idEvento}")
	public String eliminarEvento(@PathVariable int idEvento, RedirectAttributes ratt, HttpServletRequest request,
			Reserva reserva) {

		// Elimino  todas las reservas asociadas a este evento
		boolean reservasEliminadas = resdao.eliminarReservasDeEvento(idEvento);
		// Elimino el evento y se comprueba si la eliminación es correcta
		boolean eventoEliminado = edao.deleteOne(idEvento) == 1;

		// Compruebo los resultados de las eliminaciones y muestro un mensaje para
		// cada caso
		if (reservasEliminadas && eventoEliminado) {
			// Si tanto el evento como sus reservas han sido eliminados correctamente
			ratt.addFlashAttribute("mensaje", "El evento y sus reservas han sido eliminados correctamente.");
		} else if (eventoEliminado) {
			// Si solo el evento fue eliminado, pero no tenia reservas
			ratt.addFlashAttribute("mensaje", "El evento fue eliminado correctamente, pero no tenía reservas.");
		} else {
			// Si la eliminación da otro tipo de error
			ratt.addFlashAttribute("error", "El evento no pudo ser eliminado.");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + (referer != null ? referer : "/eventos");
	}

	// EDITAR EVENTO
	@PostMapping("/admin/eventos/editar/{idEvento}")
	public String editarEvento(@RequestParam int idTipo, Evento evento, RedirectAttributes ratt) {

		// Creo un objeto tipo y le asignamos el ID que se recibe desde el formulario
		Tipo tipo = new Tipo();
		tipo.setIdTipo(idTipo);

		// Se le añade este tipo al evento para actualizarlo correctamente
		evento.setTipo(tipo);

		// Se intenta actualiza el evento y mandamos un mensaje de exito o de error en cada caso
		if (edao.updateOne(evento) == 1)
			ratt.addFlashAttribute("mensaje", "Evento moficado correctamente");
		else
			ratt.addFlashAttribute("mensaje", "Evento no moficado");

		return "redirect:/home";
	}

	@GetMapping("admin/eventos/editar/{idEvento}")
	public String mostrarFormularioEdicion(@PathVariable int idEvento, Model model) {

		// Cargo la lista de tipos de evento para que el usuario pueda elegir uno
		model.addAttribute("listaTipos", tdao.findAll());
		// Busco el evento por su ID y lo enviamos al formulario para que se edite
		model.addAttribute("evento", edao.findById(idEvento));

		return "Eventos/formEventos";
	}

	// DAR DE ALTA EVENTO
	@PostMapping("admin/eventos/alta")
	public String altaEvento(@RequestParam int idTipo, Evento evento, RedirectAttributes ratt) {

		// Creo un objeto tipo y le asigno el ID que se recibe desde el formulario
		Tipo tipo = new Tipo();
		tipo.setIdTipo(idTipo);

		// Se le añade este tipo al evento para actualizarlo correctamente
		evento.setTipo(tipo);

		//Intento añadir un evento, mandamos mensaje de exito o error en cada caso.
		if (edao.insertOne(evento) == 1)
			ratt.addFlashAttribute("mensaje", "Evento dado de alta correctamente");
		else
			ratt.addFlashAttribute("mensaje", "El evento no ha sido dado de alta");

		return "redirect:/home";
	}

	@GetMapping("admin/eventos/alta")
	public String formaltaEvento(Model model) {

		// Cargo la lista de tipos de evento disponibles para que el usuario elija
		// uno en el formulario
		model.addAttribute("listaTipos", tdao.findAll());
		// Creo un objeto Evento vacío para que el formulario lo rellene
		model.addAttribute("evento", new Evento());

		return "eventos/formEventos";
	}

}
