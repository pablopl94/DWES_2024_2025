package micoservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import micoservicio.modelo.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
