package br.com.projeto.reqEstoque.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.reqEstoque.models.Mensagem;
import br.com.projeto.reqEstoque.models.Usuario;
import br.com.projeto.reqEstoque.repo.UsuarioRepository;

@Service
public class ServiceUsuario {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<?> cadastrarUsuario(Usuario usuario){
        try{
            if(usuario.getUsuario_nome().equals("")){
                mensagem.setMensagem("O nome do usuário deve ser preenchido");
                return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
            }
    
            mensagem.setMensagem("Usuário cadastrado com sucesso");
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        catch(Error e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        
    }

    public ResponseEntity<?> alterarUsuario(String id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Usuario usuario = new Usuario();

        if(usuarioOptional.isPresent()){
            usuario = usuarioOptional.get();
        }else{
            mensagem.setMensagem("Usuário não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        try{
            if(usuario.getUsuario_nome().equals("")){
                mensagem.setMensagem("O nome do usuário deve ser preenchido");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }

            mensagem.setMensagem("Usuário cadastrado com sucesso");
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        catch(Error e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> excluirUsuario(String id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Usuario usuario = new Usuario();

        if(usuarioOptional.isPresent()){
            usuario = usuarioOptional.get();
        }else{
            mensagem.setMensagem("Usuário não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        mensagem.setMensagem("Usuário excluído com sucesso");
        usuarioRepository.deleteById(usuario.getId());
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    public ResponseEntity<?> allUsers(){
        List<Usuario> usuarios =  usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    /**
     * @param login
     * @param senha
     * @return
     */
    public Usuario loginUsuario(String login, String senha){
        List<Usuario> usuarios =  usuarioRepository.findAll();
        for(Usuario usuario : usuarios){
            if (usuario.getLogin() == login && usuario.getSenha() == senha){
                return usuario;
            }
        }

        Usuario user = new Usuario();
        return user;
    }

    public ResponseEntity<?> getUsuario(String id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Usuario usuario = new Usuario();

        if(usuarioOptional.isPresent()){
            usuario = usuarioOptional.get();
        }else{
            mensagem.setMensagem("Usuário não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuario, HttpStatus.FOUND);
    }
}
