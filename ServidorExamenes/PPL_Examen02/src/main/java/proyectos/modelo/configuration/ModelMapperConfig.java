package proyectos.modelo.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proyectos.modelo.dto.ClienteDto;
import proyectos.modelo.dto.EmpleadoDto;
import proyectos.modelo.dto.FacturaDto;
import proyectos.modelo.dto.ProyectoDto;
import proyectos.modelo.entity.Cliente;
import proyectos.modelo.entity.Empleado;
import proyectos.modelo.entity.Factura;
import proyectos.modelo.entity.Proyecto;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //FACTURA A FACTURADTO
        TypeMap<Factura, FacturaDto> facturaTypeMap = modelMapper.createTypeMap(Factura.class, FacturaDto.class);
        facturaTypeMap.addMappings(mapper -> {
            mapper.map(Factura::getIdFactura, FacturaDto::setCodigoFactura);
            mapper.map(Factura::getDescripcion, FacturaDto::setDescripcion);
        });

        //CLIENTE A CLIENTEDTO
        TypeMap<Cliente, ClienteDto> clienteTypeMap = modelMapper.createTypeMap(Cliente.class, ClienteDto.class);
        clienteTypeMap.addMappings(mapper -> {
            mapper.map(Cliente::getNombre, ClienteDto::setNombre);
            mapper.map(Cliente::getDomicilio, ClienteDto::setDireccion);
        });

        //PROYECTO A PROYECTODTO
        TypeMap<Proyecto, ProyectoDto> proyectoTypeMap = modelMapper.createTypeMap(Proyecto.class, ProyectoDto.class);
        proyectoTypeMap.addMappings(mapper -> {
            mapper.map(Proyecto::getIdProyecto, ProyectoDto::setCodigoProyecto);
            mapper.map(Proyecto::getDescripcion, ProyectoDto::setDescripcion);
            mapper.map(Proyecto::getFechaInicio, ProyectoDto::setFechaInicio);
            mapper.map(Proyecto::getFechaFinReal, ProyectoDto::setFechaFinReal);
        });


        //EMPLEADO A EMPLEADODTO
        TypeMap<Empleado, EmpleadoDto> empleadoTypeMap = modelMapper.createTypeMap(Empleado.class, EmpleadoDto.class);
        empleadoTypeMap.addMappings(mapper -> {
            mapper.using(ctx -> {
                Empleado emp = (Empleado) ctx.getSource();
                return emp.getApellidos() + ", " + emp.getNombre();
            }).map(src -> src, EmpleadoDto::setNombreCompleto);
        });

        return modelMapper;
    }
}
