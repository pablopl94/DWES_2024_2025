package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.modelo.entities.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer>{

}
