package br.com.projeto.reqEstoque.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(min = 5, max = 50, message = "O campo deve ser maior-igual a 5 e menos-igual a 50")
    @NotEmpty(message = "O nome do usuário deve ser preenchido")
    private String usuario_nome;

    @Size(min = 4, max = 20, message = "O campo deve ser maior-igual a 4 e menos-igual a 20")
    @NotEmpty(message = "O login deve ser preenchido")
    private String login;

    @Size(min = 4, max = 20, message = "O campo deve ser maior-igual a 4 e menos-igual a 20")
    @NotEmpty(message = "A senha deve ser preenchido")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Setor setor;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}
