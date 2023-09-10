package br.com.projeto.reqEstoque.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reqEstoque.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{
    List<Usuario> findAll();
    Optional<Usuario> findById(String usuario_id);
}
