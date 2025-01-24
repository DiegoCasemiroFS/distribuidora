package br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao;

import br.com.DiegoCasemiroFS.distribuidora.entity.produto.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.Usuario;
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
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produtoId;

    private LocalDate dataPedido = LocalDate.now();
}
