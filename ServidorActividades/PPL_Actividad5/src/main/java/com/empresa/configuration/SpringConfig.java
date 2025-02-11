package com.empresa.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.empresa.dto.PedidoDto;
import com.empresa.entidades.Pedido;

@Configuration
public class SpringConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configurar el TypeMap para Pedido -> PedidoDto
        TypeMap<Pedido, PedidoDto> pedidoTypeMap = modelMapper.createTypeMap(Pedido.class, PedidoDto.class);
        pedidoTypeMap.addMappings(mapper -> {
            // Definir los mapeos adicionales si es necesario
            mapper.map(src -> src.getComercial().getNombre() + " " + src.getComercial().getApellido1(),
                    PedidoDto::setNombreCompletoComercial);
            mapper.map(src -> src.getCliente().getNombre() + " " + src.getCliente().getApellido1(),
                    PedidoDto::setNombreCompletoCliente);
        });


        return modelMapper;
    }
}
