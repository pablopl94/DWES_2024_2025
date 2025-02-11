package restproductos.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import restproductos.entidades.Producto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "codigo")
@Builder
public class ProductoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static ModelMapper modelMapper;
	
	private int codigo;
	@NotNull(message= "La descripcion es requerida")
	private String descripcion;
	@Min(message= "El precio  no puede ser menor que 1" , value=0)
	private double precioUnitario;
	@NotNull(message= "La marca es obligatoria")
	private String marca;
	private String color;
	@NotNull(message= "La familia es obligatoria")
	@Min(message= "La familia no puede ser menor que 1", value = 0)
	private int codigoFamilia;
	
	
	public Producto toDto() {
		
		modelMapper = new ModelMapper();
		
		Producto producto = new Producto();
		
		TypeMap<Producto, ProductoDto> typeMap = modelMapper.createTypeMap(Producto.class, ProductoDto.class);
		typeMap.addMappings(maper ->{
			maper.map(pro-> pro.getFamilia().getCodigo(), ProductoDto::setCodigoFamilia);
		});
		
		modelMapper.map(this, producto);
		
		return producto;
		
	}
	
	
	
	
	
}
