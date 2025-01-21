package eventos.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import eventos.dao.EventoDao;
import eventos.dao.ReservaDao;
import eventos.entidades.Evento;
import eventos.entidades.Reserva;
import eventos.entidades.Usuario;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/cliente/")
public class ReservaController {
	
	@Autowired
	private ReservaDao resdao;
	
	@Autowired
	private EventoDao edao;
	
	
	
	@GetMapping("/reservas/misReservas")
	public String reservasCliente(HttpSession sesion, Model model,Evento evento) {
		
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    model.addAttribute("misReservas", resdao.buscarReservasPorUsuario(usuario.getUsername()));
	    
	    return "reservas/reservasCliente";
	}
	
	
	@PostMapping("/reservas/modificar/{idReserva}")
	public String modificarMisReservas(@PathVariable int idReserva, @RequestParam int cantidad, RedirectAttributes rat) {
		
	    // Buscar la reserva por ID
	    Reserva reserva = resdao.findById(idReserva);
	    
	    // Validar la cantidad en una sola condición
	    if (cantidad < 1 || cantidad > 10) {
	        rat.addFlashAttribute("error", "La cantidad debe estar entre 1 y 10 personas.");
	        return "redirect:/cliente/reservas/misReservas";
	    }

	    // Si la cantidad es válida, actualizar los valores de la reserva
	    reserva.setCantidad(cantidad);
	    reserva.setPrecioVenta(reserva.getEvento().getPrecio().multiply(BigDecimal.valueOf(cantidad)));

	    // Guardar los cambios en la base de datos
	    if (resdao.updateOne(reserva) == 1) {
	        rat.addFlashAttribute("mensaje", "Cantidad modificada correctamente.");
	    } else {
	        rat.addFlashAttribute("error", "Error al modificar la reserva.");
	    }

	    return "redirect:/cliente/reservas/misReservas";
	}
	
	
	@GetMapping("/reservas/cancelar/{idReserva}")
	public String cancelarReservas(@PathVariable int idReserva, RedirectAttributes rat, Reserva reserva) {
		
		 if (resdao.deleteOne(idReserva) == 1) {
	            rat.addFlashAttribute("mensaje", "La reserva ha sido cancelada");
	        } else {
	            rat.addFlashAttribute("error", "La reserva no ha podido ser cancelada");
	        }
		 
	    return "redirect:/cliente/reservas/misReservas";
	}
	
	@PostMapping("/reservas/alta/{idEvento}")
	public String altaReservas(@PathVariable Integer idEvento, @RequestParam int cantidad, @RequestParam(required = false) Integer precioVenta , RedirectAttributes rat, HttpSession sesion,Model model) {

	    // Buscar el evento por ID
	    Evento evento = edao.findById(idEvento);
	    model.addAttribute("evento", evento);

	    // Validar la cantidad antes de continuar
	    if (cantidad < 1 || cantidad > 10) {
	        rat.addFlashAttribute("error", "La cantidad debe estar entre 1 y 10 personas.");
	        return "redirect:/public/eventos/detalles/" + idEvento;
	    }

	    // Obtener el usuario desde la sesión
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");


	    // Asignar valores a la reserva
	    Reserva reserva = new Reserva(); // Crear una nueva instancia de reserva
	    reserva.setEvento(evento); // Asignar el evento a la reserva
	    reserva.setUsuario(usuario); // Asignar el usuario autenticado
	    reserva.setCantidad(cantidad); // Asignar la cantidad proporcionada
	    reserva.setPrecioVenta(evento.getPrecio().multiply(BigDecimal.valueOf(cantidad))); // Calcular el precio total
	    

	    // Insertar la reserva en la base de datos
	    if (resdao.insertOne(reserva) == 1) {
	        rat.addFlashAttribute("mensaje", "Tu reserva se ha realizado correctamente.");
	    } else {
	        rat.addFlashAttribute("error", "Ya tienes una reserva para este evento. Puedes modificarla en Mis Reservas.");
	    }
	    

	    // Redirigir al inicio o a una página específica
	    return "redirect:/home";
	}
	

	
	
}





	
	
	
	



