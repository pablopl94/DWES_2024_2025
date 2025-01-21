package familias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import familias.modelo.entidades.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {
	

}
