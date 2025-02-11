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
	
	
	//MUESTRA LA LISTA DE RESERVAS DE UN USUARIO
	@GetMapping("/reservas/misReservas")
	public String reservasCliente(HttpSession sesion, Model model,Evento evento) {
		
		// Obtengo el usuario desde la sesion
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    // Busco las reservas del usuario y agregarlas al modelo
	    model.addAttribute("misReservas", resdao.buscarReservasPorUsuario(usuario.getUsername()));
	    
	    return "Reservas/reservasCliente";
	}
	
	//MODIFICAMOS LA RESERVA 
	@PostMapping("/reservas/modificar/{idReserva}")
	public String modificarMisReservas(@PathVariable int idReserva, @RequestParam int cantidad, RedirectAttributes rat) {
		
	    // Busco la reserva por ID
	    Reserva reserva = resdao.findById(idReserva);
	    
	    // Valido la cantidad de personas en la reserva 
	    if (cantidad < 1 || cantidad > 10) {
	        rat.addFlashAttribute("error", "La cantidad debe estar entre 1 y 10 personas.");
	        return "redirect:/cliente/reservas/misReservas";
	    }

	    // Si la cantidad es valida, actualiza la cantidad y el precioVenta
	    reserva.setCantidad(cantidad);
	    reserva.setPrecioVenta(reserva.getEvento().getPrecio().multiply(BigDecimal.valueOf(cantidad)));

	    // Intentamos guarda los cambios de la reserva en la bbdd, si tiene exito o error mandamos mensaje
	    if (resdao.updateOne(reserva) == 1) {
	        rat.addFlashAttribute("mensaje", "Cantidad modificada correctamente.");
	    } else {
	        rat.addFlashAttribute("error", "Error al modificar la reserva.");
	    }

	    return "redirect:/cliente/reservas/misReservas";
	}
	
	//CANCELAR LA RESERVA
	@GetMapping("/reservas/cancelar/{idReserva}")
	public String cancelarReservas(@PathVariable int idReserva, RedirectAttributes rat, Reserva reserva) {
		
		 // Intentamos eliminar la reserva, si tiene exito o error mandamos mensaje
		 if (resdao.deleteOne(idReserva) == 1) {
	            rat.addFlashAttribute("mensaje", "La reserva ha sido cancelada");
	        } else {
	            rat.addFlashAttribute("error", "La reserva no ha podido ser cancelada");
	        }
		 
	    return "redirect:/cliente/reservas/misReservas";
	}
	
	//ALTA DE UNA RESERVA
	@PostMapping("/reservas/alta/{idEvento}")
	public String altaReservas(@PathVariable Integer idEvento, @RequestParam int cantidad, RedirectAttributes rat, HttpSession sesion) {

	    // Busco el evento por ID
	    Evento evento = edao.findById(idEvento);

	    // Obtengo el usuario desde la sesion
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");

	    // Valido la cantidad de personas permitidas por reserva
	    if (cantidad < 1 || cantidad > 10) {
	        rat.addFlashAttribute("error", "La cantidad debe estar entre 1 y 10 personas.");
	        return "redirect:/public/eventos/detalles/" + idEvento;
	    }

	    // Calcular el aforo disponible en el evento
	    int aforoMaximo = evento.getAforoMaximo();
;	    int aforoDisponible = resdao.calcularAforoDisponible(idEvento, aforoMaximo);

	    // Valido si la suma de la nueva reserva y lo ya reservado (totalRservado) supera el aforo máximo
	    int totalReservados = aforoMaximo - aforoDisponible; // Total de reservas actuales
	    if ((cantidad + totalReservados) > aforoMaximo) {
	        rat.addFlashAttribute("error", "No puedes reservar " + cantidad + " plazas. Solo quedan " + aforoDisponible + " disponibles");
	        
	        return "redirect:/public/eventos/detalles/" + idEvento;
	    }

	    // Asigno valores a la nueva reserva
	    Reserva reserva = new Reserva();
	    reserva.setEvento(evento);
	    reserva.setUsuario(usuario);
	    reserva.setCantidad(cantidad);
	    // Calculo el precioVenta de la reserva multiplicando el precio del evento por la cantidad de personas
	    reserva.setPrecioVenta(
	        evento.getPrecio() // Obtiene el precio del evento (tipo BigDecimal)
	            .multiply(BigDecimal.valueOf(cantidad)) // Multiplica el precio por la cantidad de reservas
	    );

	    // Intentamos añadir la reserva a la bbdd, si tiene exito o error mandamos mensaje
	    if (resdao.insertOne(reserva) == 1) {
	        rat.addFlashAttribute("mensaje", "Tu reserva se ha realizado correctamente.");
	    } else {
	        rat.addFlashAttribute("error", "Ya tienes una reserva para este evento. Puedes modificarla en Mis Reservas.");
	    }

	    return "redirect:/home";
	}


	

	
	
}





	
	
	
	



