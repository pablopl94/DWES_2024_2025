package restproductos.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoRestController {
	
	@GetMapping("/saludo")
	public Map<String,String> saludar() {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("mensaje", "Hola saludos desde Spring Boot");
		return mapa;
	}
	
	@GetMapping("/saludo/{nombre}")
	public Map<String,String> saludarPathVariable(@PathVariable String nombre) {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("mensaje", "Hola saludos desde Spring Boot");
		mapa.put("nombre", nombre);
		return mapa;
	}
	
	@GetMapping("/saludo/parametros")
	public Map<String,String> saludarRequestParam(@RequestParam String nombre) {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("mensaje", "Hola saludos desde Spring Boot");
		mapa.put("nombre", nombre);
		return mapa;
	}


}
