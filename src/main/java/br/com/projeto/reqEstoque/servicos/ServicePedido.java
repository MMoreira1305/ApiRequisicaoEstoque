package br.com.projeto.reqEstoque.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.reqEstoque.models.Mensagem;
import br.com.projeto.reqEstoque.models.Pedido;
import br.com.projeto.reqEstoque.repo.PedidoRepository;

@Service
public class ServicePedido {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<?> fazerPedido(Pedido pedido){
        if(pedido.getProdutos().size() < 1){
            mensagem.setMensagem("É necessário ter um produto para se fazer um pedido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        pedidoRepository.save(pedido);
        mensagem.setMensagem("Pedido inserido com sucesso");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    public ResponseEntity<?> pedidoFeito(Pedido pedido){
        pedido.setFeito(true);
        pedidoRepository.save(pedido);
        mensagem.setMensagem("Pedido feito.");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllPedidos(){
        try{
            List<Pedido> pedidos = pedidoRepository.findAll();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
        catch(Error e){
            mensagem.setMensagem(e.getMessage());
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }
}
