package cajeroweb.configuration;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.entidades.TipoCuota;

@Configuration
public class BeansConfiracion {
	
	@Bean
	Prestamo getPrestamo() {
		Prestamo prestamo = new Prestamo();
		prestamo.setCantidadPrestamo(30000);
		
		Prestamo p1 = Prestamo.builder()
				.idPrestamo(123)
				.descripcion("Barco")
				.cantidadPrestamo(20000)
				.tipoCuota(TipoCuota.VARIABLE)
				.build();
		
		
	    return p1;
	}

}
