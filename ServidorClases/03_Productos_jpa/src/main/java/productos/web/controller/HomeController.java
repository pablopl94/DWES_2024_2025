package productos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import productos.web.modelo.dao.ProductoDao;




@Controller
public class HomeController {
	
	@Autowired
	private ProductoDao pdao;
	
	
	@GetMapping("/")
	public String procHome(Model model) {
		
		model.addAttribute("productos",pdao.findAll());
		return "home";
		
	}
}
