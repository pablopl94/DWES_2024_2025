package restproductos.dto;


import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import restproductos.modelo.entities.Producto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "codigo")
@Builder
public class ProductoDto implements Serializable{
	 
	
	private static final long serialVersionUID = 1L;

	private static ModelMapper modelMapper;
	
	private int codigo;
	private String descripcion;
	private double precio;
	private String marca;
	private String color;
	private int codigoFamilia;
	
	public Producto converToproducto() {
		modelMapper = new ModelMapper();
		Producto producto = new Producto();
		TypeMap<Producto, ProductoDto> typeMap = modelMapper.createTypeMap(Producto.class, ProductoDto.class);
		typeMap.addMappings(mapper -> {
		    mapper.map(src -> src.getFamilia().getCodigo(), ProductoDto::setCodigoFamilia);
		});
		modelMapper.map(this, producto);
		System.out.println(producto);
		System.out.println();
		return producto;
		
	}

}
