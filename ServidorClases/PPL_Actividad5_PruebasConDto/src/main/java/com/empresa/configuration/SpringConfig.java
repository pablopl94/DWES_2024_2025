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
        return modelMapper;
    }
}
