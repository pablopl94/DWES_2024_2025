package restproductos.configuracion;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import restproductos.dto.ProductoDto;
import restproductos.modelo.entities.Producto;

@Configuration
public class SpingConfig {
	
	
	@Bean
	ModelMapper getmapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		TypeMap<Producto, ProductoDto> typeMap = 
				modelMapper.createTypeMap(Producto.class, ProductoDto.class);
		typeMap.addMappings(maper ->{
			maper.map(pro ->  pro.getFamilia().getCodigo(), ProductoDto::setCodigoFamilia);	
			
			
		});
		
		return modelMapper;
		
		
	}

}
