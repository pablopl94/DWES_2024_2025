package com.empresa.services;

import java.util.List;
import java.util.Map;

import com.empresa.dto.PedidoDto;
import com.empresa.entidades.Comercial;
import com.empresa.entidades.Pedido;

public interface PedidoServices extends IGenericCrud<Pedido, Integer> {

	List<PedidoDto> buscarPedidosPorComercial(int idComercial); 
	
	List<Comercial> comercialesSinPedidos();
	
	Map<String, Double> obtenerImporteTotalComerciales();
	
	List<Comercial> buscarComercialesPorPedidoCliente(int idCliente);
	
}
