package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eventos.dao.EventoDao;
import eventos.dao.TipoDao;

@Controller
@RequestMapping("/public")
public class TipoController {
	
	@Autowired
	private TipoDao tdao;
	
	@Autowired
	private EventoDao edao;

	
	//LISTA DE TIPOS DE EVENTO
	@GetMapping("/tipoEvento")
	public String listaTipos(@RequestParam(required = false) Integer idTipo, Model model) {

	    // Busco todos los tipos de eventos y los añado al modelo para mostrarlos en la vista
	    model.addAttribute("tipos", tdao.findAll());

	    // Paso el idTipo recibido al modelo (si es nulo, simplemente se enviará como null)
	    model.addAttribute("idTipo", idTipo);

	    // Cuando el usuario selecciona un tipo el formulario select de la vista, filtra los eventos de ese tipo
	    if (idTipo != null) {
	        model.addAttribute("listaTipo", edao.buscarPorTipo(idTipo));
	    } else {
	        // Si no se selecciona un tipo, se envía una lista vacia a la vista
	        model.addAttribute("listaTipo", null);
	    }

	    return "Tipos/listaTipos";
	}



}
