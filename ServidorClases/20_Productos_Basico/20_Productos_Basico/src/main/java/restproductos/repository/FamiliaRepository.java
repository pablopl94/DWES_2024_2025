package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restproductos.modelo.entities.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Integer>{

}
