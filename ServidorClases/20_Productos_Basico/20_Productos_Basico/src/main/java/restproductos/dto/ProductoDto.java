package restproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "codigo")
@Builder
public class ProductoDto {
	
	private int codigo;
	private String descripcion;
	private double precio;
	private String marca;
	private String color;
	private int codigoFamilia;

}
