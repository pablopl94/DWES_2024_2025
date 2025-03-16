package restproductos.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
