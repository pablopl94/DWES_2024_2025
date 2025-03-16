package pruebawarbbdd.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebawarbbdd.modelo.entidades.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

}
