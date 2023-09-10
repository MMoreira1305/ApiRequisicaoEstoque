package br.com.projeto.reqEstoque.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.reqEstoque.dto.PedidoProdutoDTO;
import br.com.projeto.reqEstoque.repo.PedidoProdutoDTORepository;

@Service
public class PedidoProdutoDTOService {

    @Autowired
    PedidoProdutoDTORepository pedidoProdutoDTORepository;

    public List<PedidoProdutoDTO> findAllPedidos() {
        return pedidoProdutoDTORepository.findAllPedidoProdutoDTOs();
    }
}
