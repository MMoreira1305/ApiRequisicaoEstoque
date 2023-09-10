package br.com.projeto.reqEstoque.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.reqEstoque.models.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{
    List<Pedido> findAll();
}
