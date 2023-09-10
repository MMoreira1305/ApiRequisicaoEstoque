package br.com.projeto.reqEstoque.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.reqEstoque.dto.PedidoProdutoDTO;

@Repository
public interface PedidoProdutoDTORepository extends JpaRepository<PedidoProdutoDTO, Long>{
    // Fazendo query para selecionar algumas coisas necessárias para retornar para o frontend
    @Query(
    value = "SELECT "
        + "pedido.pedido_id AS pedido_id, "
        + "produto.produto_nome AS produto_nome, "
        + "produto.setor, "
        + "pedido.pedido_quantidade AS pedido_quantidade, "
        + "pedido.pedido_data AS pedido_data, "
        + "pedido.pedido_horario AS pedido_horario, "
        + "pedido.feito AS feito,"
        + "usuario.usuario_nome AS usuario_nome "
        + "FROM "
        + "pedido_produto "
        + "JOIN pedido ON pedido_produto.pedido_id = pedido.pedido_id "
        + "JOIN produto ON pedido_produto.produto_id = produto.id "
        + "JOIN usuario ON pedido.usuario_id = usuario.id;",
    nativeQuery = true)
    List<PedidoProdutoDTO> findAllPedidoProdutoDTOs();
}
