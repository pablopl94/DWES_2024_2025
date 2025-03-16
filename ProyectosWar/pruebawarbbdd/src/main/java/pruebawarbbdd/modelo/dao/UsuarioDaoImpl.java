package pruebawarbbdd.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pruebawarbbdd.modelo.entidades.Usuario;
import pruebawarbbdd.modelo.repository.IUsuarioRepository;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
        }

}
