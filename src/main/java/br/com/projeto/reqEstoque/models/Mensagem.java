package br.com.projeto.reqEstoque.models;

import org.springframework.stereotype.Component;

@Component
public class Mensagem {

    // Nesta classe, uso ela como forma de passar uma instância de classe para o método de ResponseEntity<?>
    // O deixando mais estilizado para maior entendimento e clareza no código.

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}