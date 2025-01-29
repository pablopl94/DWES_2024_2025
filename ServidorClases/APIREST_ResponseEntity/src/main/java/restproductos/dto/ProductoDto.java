package restproductos.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

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
public class ProductoDto {
	private static ModelMapper modelMapper;
	private int codigo;
	private String descripcion;
	private double precioUnitario;
	private String marca;
	private String color;
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
