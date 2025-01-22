package es.unir.excursionweb.controller;

import es.unir.excursionweb.model.dao.ExcursionDao;
import es.unir.excursionweb.model.javabean.Excursion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/excursiones")
public class ExcursionController {

	@Autowired
	private ExcursionDao exdao;

	// EDITAR UNA EXCURSION (GET Y POST)
	@PostMapping("/modificar/{idExcursion}")
	public String exFormEdicion(@PathVariable int idExcursion, RedirectAttributes ratt) {

		Excursion excursion = exdao.findById(idExcursion);
		

		if (exdao.updateOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursión modificada correctamente");
		else
			ratt.addFlashAttribute("mensaje", "Excursión no modificada correctamente");

		return "redirect:/";
	}

	@GetMapping("/modificar/{idExcursion}")
	public String exModificar(Model model, @PathVariable int idExcursion) {

		Excursion excursion = exdao.findById(idExcursion);
		if (excursion == null) {
			model.addAttribute("mensaje", "Excursión no existe");
			return "forward:/";
		}

		model.addAttribute("excursion", excursion);
		return "formModificarExcursion";
	}

	// ALTA NUEVA EXCURSION (GET Y POST)

	@PostMapping("/alta")
	public String exFormAlta(Excursion excursion, RedirectAttributes ratt) {

		if (exdao.insertOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursión creada correctamente");
		else
			ratt.addFlashAttribute("mensaje", "Excursión NO creada");

		return "redirect:/";
	}

	@GetMapping("/alta")
	public String procAlta() {
		return "formAltaExcursion";
	}

	// VER DETALLES (GET)

	@GetMapping("/detalle/{idExcursion}")
	public String VerDetalle(Model model, @PathVariable int idExcursion) {

		Excursion excursion = exdao.findById(idExcursion);
		model.addAttribute("excursion", excursion);

		return "verDetalleExcursion";
	}

	// CANCELAR EXCURSION "CAMBIAR ESTADO" (GET)
	@GetMapping("/cancelar/{idExcursion}")
	public String cancelarExcursion(@PathVariable int idExcursion, RedirectAttributes ratt) {
		Excursion excursion = exdao.findById(idExcursion);

		if (!excursion.getEstado().equals("CANCELADO")) {
			excursion.setEstado("CANCELADO");
			exdao.updateOne(excursion);
			ratt.addFlashAttribute("mensaje",
					"Excursión con destino a " + excursion.getDestino() + " ha sido cancelada.");
		} else {
			ratt.addFlashAttribute("mensaje", "La excursión ya estaba cancelada o no se encontró.");
		}

		return "redirect:/";
	}

	// FILTRO CREADOS (GET)
	@GetMapping("/creados")
	public String listarExcursionesActivas(Model model) {
		List<Excursion> excursionesCreadas = exdao.findByActivos();
		model.addAttribute("excursionesCreadas", excursionesCreadas);
		return "excursionesCreadas";
	}

	// FILTRO TERMINADOS (GET)
	@GetMapping("/terminados")
	public String listarExcursionesCanceladas(Model model) {
		List<Excursion> excursionesTerminadas = exdao.findByTerminados();
		model.addAttribute("excursionesTerminadas", excursionesTerminadas);
		return "excursionesTerminadas";
	}

	// FILTRO DESTACADOS (GET)
	@GetMapping("/destacados")
	public String listarDestacadas(Model model) {
		List<Excursion> excursionesDestacadas = exdao.findByDestacados();
		model.addAttribute("excursionesDestacadas", excursionesDestacadas);
		return "excursionesDestacadas";
	}

	// BUSQUEDA POR PRECIO MAYO QUE
	@GetMapping("/buscar/precioMayorQue")
	public String buscarPorPrecioMayorQue(@RequestParam("precio") double precio, Model model) {
		List<Excursion> excursiones = exdao.findByPrecioMayorQue(precio);
		model.addAttribute("excursiones", excursiones);
		model.addAttribute("mensaje", "Excursiones con precio mayor que " + precio);
		return "excursionesBusquedas";
	}

	// BUSQUEDA POR PRECIO MENOS QUE
	@GetMapping("/buscar/precioMenorQue")
	public String buscarPorPrecioMenorQue(@RequestParam("precio") double precio, Model model) {
		List<Excursion> excursiones = exdao.findByPrecioMenorQue(precio);
		model.addAttribute("excursiones", excursiones);
		model.addAttribute("mensaje", "Excursiones con precio menor que " + precio);
		return "excursionesBusquedas";
	}

	// BUSQUEDA RANGO DE PRECIO
	@GetMapping("/buscar/precioEntre")
	public String buscarPorRangoDePrecio(@RequestParam("min") double precioMin, @RequestParam("max") double precioMax,
			Model model) {
		List<Excursion> excursiones = exdao.findByPrecioEntre(precioMin, precioMax);
		model.addAttribute("excursiones", excursiones);
		model.addAttribute("mensaje", "Excursiones con precio entre " + precioMin + " y " + precioMax);
		return "excursionesBusquedas";
	}

	// BUSQUEDA POR ORIGEN O DESTINO
	@GetMapping("/buscar/origenDestino")
	public String buscarPorPalabraEnOrigenODestino(@RequestParam("palabra") String palabra, Model model) {
		List<Excursion> excursiones = exdao.findByOrigenDestino(palabra);
		model.addAttribute("excursiones", excursiones);
		model.addAttribute("mensaje", "Excursiones que contienen '" + palabra + "' en origen o destino");
		return "excursionesBusquedas";
	}
}
