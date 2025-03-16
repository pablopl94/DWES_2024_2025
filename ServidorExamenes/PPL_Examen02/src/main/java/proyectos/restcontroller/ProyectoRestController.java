package proyectos.restcontroller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import proyectos.modelo.dto.ClienteDto;
import proyectos.modelo.dto.EmpleadoDto;
import proyectos.modelo.dto.FacturaDto;
import proyectos.modelo.dto.ProyectoDto;
import proyectos.modelo.entity.Factura;
import proyectos.modelo.entity.Proyecto;
import proyectos.modelo.service.FacturaService;
import proyectos.modelo.service.ProyectoConEmpleadosService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto")
public class ProyectoRestController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ProyectoConEmpleadosService proyectoconempleadosServices;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/factura/{idProyecto}")
    public FacturaDto obtenerFactura(@PathVariable String idProyecto) {
    	
        Factura factura = facturaService.buscarFacturaPorProyecto(idProyecto);
        FacturaDto facturaDto = mapper.map(factura, FacturaDto.class);
        facturaDto.setFechaFactura(LocalDate.now());

        Proyecto proyecto = factura.getProyecto();
        facturaDto.setProyecto(mapper.map(proyecto, ProyectoDto.class));
        facturaDto.setCliente(mapper.map(proyecto.getCliente(), ClienteDto.class));

        List<EmpleadoDto> empleadosDto = proyectoconempleadosServices.buscarProyectoConEmpleadoPorProyecto(idProyecto)
            .stream()
            .map(proyectoConEmpleado -> {
                EmpleadoDto empleadoDto = mapper.map(proyectoConEmpleado.getEmpleado(), EmpleadoDto.class);
                empleadoDto.setTotalHoras(proyectoConEmpleado.getHorasAsignadas());
                empleadoDto.setImporteRepercutido(
                        (proyectoConEmpleado.getEmpleado().getSalario() / 1800) * proyectoConEmpleado.getHorasAsignadas());
                return empleadoDto;
            }).collect(Collectors.toList());

        facturaDto.setEmpleados(empleadosDto);
        facturaDto.setTotalHoras(empleadosDto.stream().mapToInt(EmpleadoDto::getTotalHoras).sum());
        facturaDto.setTotalFacturado(empleadosDto.stream().mapToDouble(EmpleadoDto::getImporteRepercutido).sum());
        facturaDto.setImporteVentaPrevisto(proyecto.getVentaPrevisto());

        return facturaDto;
    }
    
    
}
