package br.com.projeto.reqEstoque.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reqEstoque.models.Produto;
import br.com.projeto.reqEstoque.models.Setor;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String>{
    List<Produto> findAll();

    Optional<Produto> findById(String produto_id);

    void delete(Produto findById);

    List<Produto> findBySetor(Setor setor);
}
