package cajeroweb.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cajeroweb.modelo.dao.PrestamoDao;
import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.entidades.TipoCuota;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
	@Autowired
	private Prestamo prestamo;
	
	@Autowired
	private PrestamoDao pdao;
	
	@GetMapping("/home")
	public String getMethodName(Model model, HttpSession sesion) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		model.addAttribute("prestamos", pdao.buscarPorCuenta(cuenta.getIdCuenta()));
		
		return "homePrestamo";
	}
	
	
	@GetMapping("/eliminar/{idPrestamo}")
	public String eliminar(@PathVariable int idPrestamo, Model model) {
		if (pdao.eliminarPrestamo(idPrestamo) ==1)
			model.addAttribute("mensaje","Prestamo eliminado correctamente");
		else
			model.addAttribute("mensaje","Prestamo NOOO eliminado");
		
		return "forward:/prestamos/home";
		
			
	}
	
	@GetMapping("/alta")
	public String sacarFormAlata() {
		System.out.println(prestamo);
		return "FormAltaPrestamo";
	}
	
	@PostMapping("/alta")
	public String procAlta(Prestamo prestamo, RedirectAttributes ratt,
			HttpSession sesion) {
		prestamo.setCuenta((Cuenta)sesion.getAttribute("cuenta"));
		prestamo.setFechaPrestamo(new Date());
	//	prestamo.setTipoCuota(TipoCuota.FIJO);
		
		if (pdao.altaPrestamo(prestamo) != null)
			ratt.addFlashAttribute("mensaje", "Alta Confirmada");
		else
			ratt.addFlashAttribute("mensaje", "Alta NOOO Confirmada");
		
		return "redirect:/prestamos/home";
	}
	
	
	

}
