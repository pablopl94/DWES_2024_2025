package com.empresa.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.dto.PedidoDto;
import com.empresa.entidades.Comercial;
import com.empresa.entidades.Pedido;
import com.empresa.repository.ComercialRepository;
import com.empresa.repository.PedidoRepository;

@Service
public class PedidoServicesImpl implements PedidoServices {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ComercialRepository comercialRepository;
	


	
	@Override
	public Pedido findById(Integer clavePk) {
		return pedidoRepository.findById(clavePk).orElse(null);
	}

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido insertOne(Pedido entidad) {
		try {
			if(pedidoRepository.existsById(entidad.getIdPedido())) {
				return null;
			}else {
				return pedidoRepository.save(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pedido updateOne(Pedido entidad) {
		try {
			if(pedidoRepository.existsById(entidad.getIdPedido())) {
				return pedidoRepository.save(entidad);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteOne(Integer clavePk) {
		try {
			if(pedidoRepository.existsById(clavePk)) {
				pedidoRepository.deleteById(clavePk);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<PedidoDto> buscarPedidosPorComercial(int idComercial) {
	    List<Pedido> listaPedidos = pedidoRepository.findByComercial_IdComercial(idComercial);

	    // AQUI no uso ModelMapper para pasar a Dto porque me daba error, al sacar el nombrecompleto del comercial
	    // y cliente . He tenido que hacer el filtro en el dto para pasarlo a dto y usarla aqui.
	    return listaPedidos.stream()
                .map(pedido -> new PedidoDto().fromEntity(pedido))
                .collect(Collectors.toList());
	}
	
	@Override
	public List<Comercial> comercialesSinPedidos() {
        List<Comercial> todosLosComerciales = comercialRepository.findAll();

        return todosLosComerciales.stream()
                .filter(comercial -> pedidoRepository.findByComercial_IdComercial(comercial.getIdComercial()).isEmpty())
                .collect(Collectors.toList());
    }

	@Override
	public Map<String, Double> obtenerImporteTotalComerciales() {
	    List<Pedido> pedidos = pedidoRepository.findAll();
	    return pedidos.stream()
	            .collect(Collectors.groupingBy(
	                p -> p.getComercial().getNombre() + " " +
	                     p.getComercial().getApellido1() + " " +
	                     p.getComercial().getApellido2(),
	                Collectors.summingDouble(Pedido::getImporte)
	            ));
	}

	@Override
	public List<Comercial> buscarComercialesPorPedidoCliente(int idCliente) {
		return pedidoRepository.findComercialesByPedidosCliente(idCliente);
	}

}

