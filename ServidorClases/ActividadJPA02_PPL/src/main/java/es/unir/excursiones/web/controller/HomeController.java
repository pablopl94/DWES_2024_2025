package es.unir.excursiones.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.unir.excursiones.web.modelo.dao.ExcursionDao;


@Controller
public class HomeController {

	@Autowired
	private ExcursionDao exdao;

	@GetMapping("/")
	public String webHome(Model model) {
		model.addAttribute("excursiones", exdao.findAll());
		return "home";
	}

}