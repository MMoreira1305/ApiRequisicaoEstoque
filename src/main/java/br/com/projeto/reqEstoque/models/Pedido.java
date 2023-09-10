package br.com.projeto.reqEstoque.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Pedido")
@Table(name = "Pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pedido {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido_id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate pedido_data;

    @Column(columnDefinition = "TIME")
    private LocalTime pedido_horario;

    private int pedido_quantidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Setor setor;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @NonNull
    private Boolean feito;
}
