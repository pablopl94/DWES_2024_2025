package proyectos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProyectoDto {
    private String codigoProyecto;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFinReal;
}