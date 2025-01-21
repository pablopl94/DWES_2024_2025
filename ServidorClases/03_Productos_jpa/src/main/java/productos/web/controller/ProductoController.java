package productos.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import productos.web.modelo.dao.ProductoDao;
import productos.web.modelo.entidades.Producto;



@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoDao pdao;
	
	@PostMapping("/editar/{idProducto}")
	public String procFormEdicion(@PathVariable long idProducto, Producto producto,  RedirectAttributes ratt) {
	 
		producto.setIdProducto(idProducto);
		
		if (pdao.updateOne(producto) == 1)
			ratt.addFlashAttribute("mensaje", "Producto moficado correctamente");
		
		else 
			ratt.addFlashAttribute("mensaje", "Producto no moficado correctamente");
		
		return "redirect:/"; //para la tarea, quema los datos y solo sobrevive el ratt
	}
		
	@GetMapping("/editar/{idProducto}")
	public String procModificar(Model model,@PathVariable long idProducto) {
		
			Producto producto = pdao.findById(idProducto);
			if (producto == null) {
				model.addAttribute("mensaje", "Producto no existe");
				return "forward:/";			
				}
			
			model.addAttribute("producto",producto);
			
			return "formEdicionProducto";
	}
	
	
	
	
	@PostMapping("/alta")
	public String procFormAlta(Producto producto, RedirectAttributes ratt) {
		
		if (pdao.insertOne(producto)== 1)
			ratt.addFlashAttribute("mensaje", "Alta Realizada");
		
		else
			ratt.addFlashAttribute("mensaje", "Alta NO Realizada");
		
		return "redirect:/";
	}
	
	@GetMapping("/alta")
	public String procAlta() {
		
			return "formAltaProducto";
	}
	
	@GetMapping("/detalle/{idProducto}")
	public String VerDetalle(Model model, @PathVariable long idProducto) {
		
	    Producto producto = pdao.findById(idProducto);
	    model.addAttribute("producto", producto);
	    
	    return "VerDetalle";
	}
	
	
	
	@GetMapping("/eliminar/{idProducto}")
	public String eliminar (Model model, @PathVariable long idProducto) {
		
	    int filas = pdao.deleteOne(idProducto);
	    
	    if (filas == 1)
	    	 model.addAttribute("mensaje","producto eliminado");
	    else
	    	 model.addAttribute("mensaje","producto no eliminado");
	 
	    
	    return "forward:/";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}



