package proyectos.modelo.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FacturaDto {
	
    private String codigoFactura;
    private String descripcion;
    private LocalDate fechaFactura;
    private ClienteDto cliente;
    private ProyectoDto proyecto;
    private List<EmpleadoDto> empleados;
    private int totalHoras;
    private double importeVentaPrevisto;
    private double totalFacturado;
}