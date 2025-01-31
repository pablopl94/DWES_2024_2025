package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.entidades.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {

}
