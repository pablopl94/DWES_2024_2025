package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entidades.Comercial;
import com.empresa.entidades.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("SELECT p.comercial FROM Pedido p WHERE p.cliente.idCliente =?1 " )
	List<Comercial> findComercialesByPedidosCliente(int idCliente);
	
	List<Pedido> findByComercial_IdComercial(int idComercial);
}
