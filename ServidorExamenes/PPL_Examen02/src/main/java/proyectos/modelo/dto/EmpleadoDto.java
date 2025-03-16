package proyectos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmpleadoDto {

    private String nombreCompleto;
    private int totalHoras;
    private double importeRepercutido;
}
