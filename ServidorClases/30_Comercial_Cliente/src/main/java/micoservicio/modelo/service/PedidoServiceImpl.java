package micoservicio.modelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micoservicio.modelo.entities.Pedido;
import micoservicio.repository.PedidoRepository;
@Service
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	PedidoRepository prepo;

	@Override
	public Pedido buscarUno(int idpedido) {
		// TODO Auto-generated method stub
		return prepo.findById(idpedido).orElse(null);
	}
	
	

}
