package es.unir.excursionweb.model.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import es.unir.excursionweb.model.javabean.Excursion;

@Repository
public class ExcursionDaoImpList implements ExcursionDao {

	private List<Excursion> lista;

	public ExcursionDaoImpList() {
		lista = new ArrayList<>();
		cargarDatos();
	}

	private void cargarDatos() {
		lista.add(new Excursion(1, "Madrid", "Barcelona", java.time.LocalDate.now().plusDays(3), 2, "CREADO", "S", 50,
				10, 150.00, "imagen1.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(2, "Sevilla", "Lisboa", java.time.LocalDate.now().plusDays(5), 3, "CREADO", "N", 40, 15,
				200.00, "imagen2.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(3, "Bilbao", "París", java.time.LocalDate.now().plusDays(10), 4, "TERMINADO", "S", 70,
				20, 300.00, "imagen3.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(4, "Madrid", "Nueva York", java.time.LocalDate.now().plusDays(15), 7, "CREADO", "S",
				100, 50, 1200.00, "imagen4.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(5, "Barcelona", "Roma", java.time.LocalDate.now().plusDays(20), 5, "CANCELADO", "N", 80,
				25, 450.00, "imagen5.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(6, "Valencia", "Londres", java.time.LocalDate.now().plusDays(25), 3, "CREADO", "S", 60,
				30, 400.00, "imagen6.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(7, "Granada", "Marrakech", java.time.LocalDate.now().plusDays(30), 4, "TERMINADO", "N",
				50, 20, 500.00, "imagen7.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(8, "Sevilla", "Buenos Aires", java.time.LocalDate.now().plusDays(35), 10, "CREADO", "N",
				120, 60, 1500.00, "imagen8.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(9, "Madrid", "Tokio", java.time.LocalDate.now().plusDays(40), 12, "CREADO", "S", 150,
				80, 1800.00, "imagen9.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(10, "Bilbao", "Berlín", java.time.LocalDate.now().plusDays(45), 3, "CANCELADO", "N", 75,
				30, 380.00, "imagen10.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(11, "Barcelona", "Ámsterdam", java.time.LocalDate.now().plusDays(50), 4, "CREADO", "S",
				85, 35, 420.00, "imagen11.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(12, "Madrid", "Miami", java.time.LocalDate.now().plusDays(55), 9, "CREADO", "N", 130,
				70, 1300.00, "imagen12.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(13, "Valencia", "Bruselas", java.time.LocalDate.now().plusDays(60), 3, "TERMINADO", "S",
				60, 25, 350.00, "imagen13.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(14, "Granada", "Ciudad de México", java.time.LocalDate.now().plusDays(65), 10, "CREADO",
				"N", 140, 80, 1600.00, "imagen14.jpg", java.time.LocalDate.now()));
		lista.add(new Excursion(15, "Sevilla", "Sídney", java.time.LocalDate.now().plusDays(70), 14, "CREADO", "S", 150,
				90, 2200.00, "imagen15.jpg", java.time.LocalDate.now()));

	}

	// METODO PARA BUSCAR POR ID
	@Override
	public Excursion findById(int idExcursion) {
		for (Excursion excursion : lista) {
			if (excursion.getIdExcursion() == idExcursion) {
				return excursion;
			}
		}
		return null;
	}

	// METODO PARA INSERTAR
	@Override
	public int insertOne(Excursion excursion) {
		if (lista.contains(excursion))
			return 0;
		return lista.add(excursion) ? 1 : 0;
	}

	// METODO PARA ACTUALIZAR EXCURSION
	@Override
	public int updateOne(Excursion excursion) {
		int pos = lista.indexOf(excursion);

		if (pos == 1)
			return 0;
		return (lista.set(pos, excursion) != null) ? 1 : 0;

	}

	// METODO PARA BUSCAR TODAS LAS EXCURSIONES
	@Override
	public List<Excursion> findAll() {
		return lista;
	}

	// METODO PARA BUSCAR POR EXCURSIONES CREADAS
	@Override
	public List<Excursion> findByActivos() {
		List<Excursion> creadas = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getEstado().equals("CREADO")) {
				creadas.add(excursion);
			}
		}
		return creadas;
	}

	// METODO PARA BUSCAR POR EXCURSIONES TERMINADAS
	@Override
	public List<Excursion> findByTerminados() {
		List<Excursion> terminados = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getEstado().equals("TERMINADO")) {
				terminados.add(excursion);
			}
		}
		return terminados;
	}

	// METODO PARA BUSCAR POR EXCURSIONES DESTACADAS "S"
	@Override
	public List<Excursion> findByDestacados() {
		List<Excursion> destacadas = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getDestacado().equals("S")) {
				destacadas.add(excursion);
			}
		}
		return destacadas;
	}

	// METODO PARA BUSCAR PRECIO MAYOR QUE
	@Override
	public List<Excursion> findByPrecioMayorQue(double precioMin) {
		List<Excursion> result = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getPrecioUnitario() > precioMin) {
				result.add(excursion);
			}
		}
		return result;
	}

	// METODO PARA BUSCAR PRECIO MENOR QUE
	@Override
	public List<Excursion> findByPrecioMenorQue(double precioMax) {
		List<Excursion> result = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getPrecioUnitario() < precioMax) {
				result.add(excursion);
			}
		}
		return result;
	}

	// METODO PARA BUSCAR PRECIO POR RANGO DE PRECIO
	@Override
	public List<Excursion> findByPrecioEntre(double precioMin, double precioMax) {
		List<Excursion> result = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getPrecioUnitario() >= precioMin && excursion.getPrecioUnitario() <= precioMax) {
				result.add(excursion);
			}
		}
		return result;
	}
	
	// METODO PARA BUSCAR POR PALABRA ORIGEN O DESTINO
	@Override
	public List<Excursion> findByOrigenDestino(String palabra) {
		List<Excursion> result = new ArrayList<>();
		for (Excursion excursion : lista) {
			if (excursion.getOrigen().toLowerCase().contains(palabra.toLowerCase())
					|| excursion.getDestino().toLowerCase().contains(palabra.toLowerCase())) {
				result.add(excursion);
			}
		}
		return result;
	}
}