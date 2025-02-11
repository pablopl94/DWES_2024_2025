package com.empresa.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.empresa.entidades.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "idPedido")
@Builder
public class PedidoDto {
	
	private static ModelMapper modelMapper;
	private int idPedido;
	private String nombreCompletoComercial;
	private String nombreCompletoCliente;
	private double importe;
	private LocalDate fecha;

	 public PedidoDto fromEntity(Pedido pedido) {
		 
		 modelMapper = new ModelMapper();
		 
	        PedidoDto dto = modelMapper.map(pedido, PedidoDto.class);
	        
	        if (pedido.getComercial() != null) {
	            dto.setNombreCompletoComercial(pedido.getComercial().getNombre() + " " +
	                                           pedido.getComercial().getApellido1() + " " +
	                                           pedido.getComercial().getApellido2());
	        }
	        if (pedido.getCliente() != null) {
	            dto.setNombreCompletoCliente(pedido.getCliente().getNombre() + " " +
	                                         pedido.getCliente().getApellido1() + " " +
	                                         pedido.getCliente().getApellido2());
	        }
	        return dto;
	    }

	}

