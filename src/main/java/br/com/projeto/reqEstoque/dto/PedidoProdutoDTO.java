package br.com.projeto.reqEstoque.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.projeto.reqEstoque.models.Pedido;
import br.com.projeto.reqEstoque.models.Produto;
import br.com.projeto.reqEstoque.models.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoDTO {
    @Id
    private Long pedido_id;
    private String produtoNome;
    private String setor;
    private Integer pedidoQuantidade;
    private LocalDate pedidoData;
    private LocalTime pedidoHorario;
    private Boolean feito;
    private String usuarioNome;

    // Getters e setters

    public PedidoProdutoDTO(Pedido pedido, Produto produto, Usuario usuario){
        this.pedido_id = pedido.getPedido_id();
        this.produtoNome = produto.getProduto_nome();
        this.setor = pedido.getSetor().toString();
        this.pedidoQuantidade = pedido.getPedido_quantidade();
        this.pedidoHorario = pedido.getPedido_horario();
        this.pedidoData = pedido.getPedido_data();
        this.feito = pedido.getFeito();
        this.usuarioNome = usuario.getUsuario_nome();
    }
}
