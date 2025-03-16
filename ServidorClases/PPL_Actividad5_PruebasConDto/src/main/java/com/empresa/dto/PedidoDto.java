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
	
	
	private int idPedido;
	private double importe;
	private LocalDate fecha;


}
