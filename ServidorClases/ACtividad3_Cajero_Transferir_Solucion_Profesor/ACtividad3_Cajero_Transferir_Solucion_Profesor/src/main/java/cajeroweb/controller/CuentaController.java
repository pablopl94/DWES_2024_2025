package cajeroweb.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;
import jakarta.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	
	@Autowired
	private CuentaDao cdao;
	@Autowired
	private MovimientoDao mdao;
	
	@GetMapping("/ingresar")
	public String ingresar() {
		return "Ingresar";
	}
		
	@PostMapping("/ingresar")
	public String procIngresar(@RequestParam double importe, HttpSession sesion) {
		
		Cuenta cuenta = (Cuenta)sesion.getAttribute("cuenta");
		
		cuenta.operar(importe,true);
		Movimiento mov = new Movimiento();
		mov.setCantidad(importe);
		mov.setCuenta(cuenta);
		mov.setFecha(new Date());
		mov.setOperacion("Ingreso");
		cdao.modificarCuenta(cuenta);
		mdao.altaMovimiento(mov);
		
		return "redirect:/home";
	}
	
	@GetMapping("/extraer")
	public String extraer() {
		return "Extraer";
	}
		
	@PostMapping("/extraer")
	public String procExtraer(@RequestParam double importe, HttpSession sesion,
			RedirectAttributes ratt) {
		
		Cuenta cuenta = (Cuenta)sesion.getAttribute("cuenta");
		
		if (cuenta.operar(importe, false)) {
			Movimiento mov = new Movimiento();
			mov.setCantidad(importe);
			mov.setCuenta(cuenta);
			mov.setFecha(new Date());
			mov.setOperacion("Extracción");
			cdao.modificarCuenta(cuenta);
			mdao.altaMovimiento(mov);
		
			return "redirect:/home";
		} else{
			ratt.addFlashAttribute("mensaje", "no hay dinero suficiente para extraer");
			return "redirect:/cuenta/extraer";
			
			
		}
		
	}
	
	@GetMapping("/transferir")
	public String transferir() {
		return "Transferir";
	}
	
//	@Transactional	
	@PostMapping("/transferir")
	public String procTransferir(@RequestParam int idCuenta, 
			@RequestParam double importe, HttpSession sesion,
			RedirectAttributes ratt) {
		
		Cuenta cuenta = (Cuenta)sesion.getAttribute("cuenta");
		Cuenta cuentaDestino = cdao.buscarUna(idCuenta);
		if (cuentaDestino == null) {
			ratt.addFlashAttribute("mensaje", "Cuenta no existe");
			return "redirect:/cuenta/transferir";
		}
		
		if (!cuenta.operar(importe,true)) {
			ratt.addFlashAttribute("mensaje", "no hay dinero suficiente para extraer");
			return "redirect:/cuenta/transferir";
		}
			
		Movimiento mov = new Movimiento();
			mov.setCantidad(importe);
			mov.setCuenta(cuenta);
			mov.setFecha(new Date());
			mov.setOperacion("Extracción por Transferencia");
			cdao.modificarCuenta(cuenta);
			mdao.altaMovimiento(mov);
			
		Movimiento mov2 = new Movimiento();
			mov2.setCantidad(importe);
			mov2.setCuenta(cuentaDestino);
			mov2.setFecha(new Date());
			mov2.setOperacion("Ingreso por Transferencia");
			cuentaDestino.operar(importe,true);
			cdao.modificarCuenta(cuentaDestino);
			mdao.altaMovimiento(mov2);	
		
			return "redirect:/home";
			
			
		}
		
	
	
	@GetMapping("/movimientos")
	public String movimientos(Model model, HttpSession sesion) {
		Cuenta cuenta = (Cuenta)sesion.getAttribute("cuenta"); 
		model.addAttribute("movimientos", mdao.buscarMovPorCuenta(cuenta.getIdCuenta()));
		return "Movimientos";
	}
	
	

}
