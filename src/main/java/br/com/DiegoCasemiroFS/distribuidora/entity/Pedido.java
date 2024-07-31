package br.com.DiegoCasemiroFS.distribuidora.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Produto produtoId;

    private Integer quantity;

    private LocalDate orderDate = LocalDate.now();
}
