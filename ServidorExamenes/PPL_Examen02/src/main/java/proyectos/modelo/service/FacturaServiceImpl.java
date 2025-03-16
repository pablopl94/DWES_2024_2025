package proyectos.modelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.modelo.entity.Factura;
import proyectos.modelo.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository frepo;

	@Override
	public Factura buscarFacturaPorProyecto(String idProyecto) {
		return frepo.findByProyecto_IdProyecto(idProyecto);
	}






}
