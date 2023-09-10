package br.com.projeto.reqEstoque.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.reqEstoque.models.Mensagem;
import br.com.projeto.reqEstoque.models.Produto;
import br.com.projeto.reqEstoque.models.Setor;
import br.com.projeto.reqEstoque.repo.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceProduto {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<?> adicionarProduto(Produto produto) {
        try {
            mensagem.setMensagem("Produto inserido com sucesso");
            produtoRepository.save(produto);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro ao adicionar o produto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> editarProduto(String idt){

        Produto product = new Produto();

        Optional<Produto> produtoOptional = produtoRepository.findById("seuIdAqui");
        if (produtoOptional.isPresent()){
            product = produtoOptional.get();
        }else{
            mensagem.setMensagem("Produto não encontrado com esse ID");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        try{
            if(product.getProduto_nome().equals("")){
                mensagem.setMensagem("O nome do produto deve ser preenchido");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }
    
            else if(product.getSetor().equals("")){
                mensagem.setMensagem("O setor deve ser preenchido");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }
            else{
                mensagem.setMensagem("Produto inserido com sucesso");
                produtoRepository.save(product);
                return new ResponseEntity<>(mensagem, HttpStatus.OK);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> excluirProduto(String id){
        try{
            produtoRepository.deleteById(id);
            mensagem.setMensagem("Produto deletado com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException ex) {
            // Tratamento para quando o objeto não é encontrado
            mensagem.setMensagem("O produto não foi encontrado.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> allProdutos(){
        try{
            List<Produto> produtos = produtoRepository.findAll();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> produtosTradicional(Setor setor){
        try{
            List<Produto> produtosTrad = produtoRepository.findBySetor(setor);
            return new ResponseEntity<>(produtosTrad, HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
