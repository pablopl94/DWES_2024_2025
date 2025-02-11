package restproductos.modelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Producto;
import restproductos.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository prepo;
	
	@Override
	public Producto alta(Producto producto) {
		try {
			if (prepo.existsById(producto.getCodigo()))
				return null;
			else  
				return prepo.save(producto);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public Producto modificar(Producto producto) {
		try {
			if (prepo.existsById(producto.getCodigo()))
				return prepo.save(producto);
			else  
				return null;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(int codigo) {
		try {
			if (prepo.existsById(codigo)) {
				prepo.deleteById(codigo);
				return 1;
			}
			else  
				return 0;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Producto buscarUna(int codigo) {
		// TODO Auto-generated method stub
		return prepo.findById(codigo).orElse(null);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	@Override
	public List<Producto> buscarPorFamilia(int codigoFamilia) {
		// TODO Auto-generated method stub
		return prepo.findByFamilia(codigoFamilia);
	}

	@Override
	public List<Producto> buscarPorMarcaYColor(String marca, String color) {
		// TODO Auto-generated method stub
		return prepo.findByMarcaAndColor(marca, color);
	}

	@Override
	public double mediaPrecioProdPorFamilia(int codigoFamilia) {
		List<Producto> lista = prepo.findByFamilia(codigoFamilia);
	/*	if (lista.size()==0)
			return 0;
		double suma =0;
		for(Producto p: lista) {
			suma += p.getPrecioUnitario(); 
		}
		
	//	return suma / lista.size();
	*/	
		return lista.stream()
							.mapToDouble(p -> p.getPrecioUnitario())
							.average()
							.orElse(0.0);
		
		
		
		
		
		
	}

	@Override
	public List<Producto> sbCadenaDeDescripcion(String subCadena) {
		// TODO Auto-generated method stub
		return prepo.findByDescripcionContaining(subCadena);
	}

}
