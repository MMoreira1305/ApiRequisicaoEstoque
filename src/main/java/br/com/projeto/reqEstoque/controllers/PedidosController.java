package br.com.projeto.reqEstoque.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projeto.reqEstoque.dto.PedidoProdutoDTO;
import br.com.projeto.reqEstoque.models.Mensagem;
import br.com.projeto.reqEstoque.models.Pedido;
import br.com.projeto.reqEstoque.models.Produto;
import br.com.projeto.reqEstoque.models.Usuario;
import br.com.projeto.reqEstoque.repo.PedidoRepository;
import br.com.projeto.reqEstoque.repo.ProdutoRepository;
import br.com.projeto.reqEstoque.repo.UsuarioRepository;
import br.com.projeto.reqEstoque.servicos.PedidoProdutoDTOService;
import br.com.projeto.reqEstoque.servicos.ServicePedido;
import br.com.projeto.reqEstoque.servicos.ServiceProduto;
import br.com.projeto.reqEstoque.servicos.ServiceUsuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
public class PedidosController {

    @Autowired
    PedidoProdutoDTOService pedidoProdutoDTOService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ServicePedido pedidoService;

    @Autowired
    ServiceUsuario usuarioService;

    @Autowired
    ServiceProduto produtoService;

    @Autowired
    Mensagem mensagem;

    @GetMapping("usuarios/login")
    public ResponseEntity<?> viewLogin(@RequestBody String login, String senha){
        try{
            Usuario usuario = usuarioService.loginUsuario(login, senha);
            return new ResponseEntity<>(usuario, HttpStatus.FOUND);
        }
        catch(EntityNotFoundException e){
            mensagem.setMensagem("Usuário não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> viewUsuario(){
        return usuarioService.allUsers();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> postUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUnicoUsuario(@PathVariable String id){
        try{
            return usuarioService.getUsuario(id);
        }
        catch(EntityNotFoundException e){
            mensagem.setMensagem("Usuario não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> putUsuario(@Valid @PathVariable String id){
        return usuarioService.alterarUsuario(id);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable String id){
        return usuarioService.excluirUsuario(id);
    }

    @GetMapping("/produtos")
    public ResponseEntity<?> getProdutos(){
        return produtoService.allProdutos();
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> postProdutos(@Valid @RequestBody Produto produto){
        return produtoService.adicionarProduto(produto);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> getUnicoProduto(@PathVariable String id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        Produto produto = produtoOptional.get();
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> putProdutos(@PathVariable String id){
        return produtoService.editarProduto(id);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deleteProdutosById(@PathVariable String id){
        return produtoService.excluirProduto(id);
    }

    @GetMapping("estoque/pedido")
    public ResponseEntity<?> getPedidos(){
        try{
            List<PedidoProdutoDTO> pedidos = pedidoProdutoDTOService.findAllPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }catch(Error e){
            mensagem.setMensagem(e.getMessage());
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("estoque/pedido")
    public ResponseEntity<?> postPedidos(@RequestBody Pedido pedido){
        // Aqui você recebe o objeto Pedido do cliente
        
        // Você pode acessar as propriedades do pedido, como data e horário, diretamente
        // pedido.getData();
        // pedido.getHorario();

        // Agora você precisa extrair os IDs dos produtos e do usuário do pedido
        List<String> idProdutos = pedido.getProdutos().stream().map(Produto::getId).collect(Collectors.toList());
        Optional<Usuario> userOptional = usuarioRepository.findById(pedido.getUsuario().getId());
        Usuario user = userOptional.get();
        List<Produto> listaProdutos = new ArrayList<Produto>();

        for(String produto : idProdutos){
            Optional<Produto> productOptional = produtoRepository.findById(produto);
            Produto product = productOptional.get();
            listaProdutos.add(product);
        }

        LocalDate dataAtual = LocalDate.now();
        LocalTime horaAtual = LocalTime.now();
        
        // Em seguida, você pode criar lógica para associar os produtos e o usuário ao pedido
        // e salvar o pedido no banco de dados
        
        // ... Lógica para criar o pedido ...

        Pedido ped = new Pedido(
            pedido.getPedido_id(), 
            dataAtual, 
            horaAtual, 
            pedido.getPedido_quantidade(), 
            user,
            pedido.getSetor(),
            listaProdutos,
            pedido.getFeito()
            );

        return pedidoService.fazerPedido(ped);
    }

    @PutMapping("estoque/pedido/{id}")
    public ResponseEntity<?> putPedidos(@RequestBody Pedido pedido){
        // Aqui você recebe o objeto Pedido do cliente
        
        // Você pode acessar as propriedades do pedido, como data e horário, diretamente
        // pedido.getData();
        // pedido.getHorario();

        // Agora você precisa extrair os IDs dos produtos e do usuário do pedido
        List<String> idProdutos = pedido.getProdutos().stream().map(Produto::getId).collect(Collectors.toList());
        Optional<Usuario> userOptional = usuarioRepository.findById(pedido.getUsuario().getId());
        Usuario user = userOptional.get();
        List<Produto> listaProdutos = new ArrayList<Produto>();

        for(String produto : idProdutos){
            Optional<Produto> productOptional = produtoRepository.findById(produto);
            Produto product = productOptional.get();
            listaProdutos.add(product);
        }

        LocalDate dataAtual = LocalDate.now();
        LocalTime horaAtual = LocalTime.now();
        
        // Em seguida, você pode criar lógica para associar os produtos e o usuário ao pedido
        // e salvar o pedido no banco de dados
        
        // ... Lógica para criar o pedido ...

        Pedido ped = new Pedido(
            pedido.getPedido_id(), 
            dataAtual, 
            horaAtual, 
            pedido.getPedido_quantidade(), 
            user,
            pedido.getSetor(),
            listaProdutos,
            pedido.getFeito()
            );

        return pedidoService.pedidoFeito(ped);
    }
}
