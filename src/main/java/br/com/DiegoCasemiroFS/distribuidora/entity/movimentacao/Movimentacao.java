package br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao;

import br.com.DiegoCasemiroFS.distribuidora.entity.produto.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.usuario.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private User userId;

    private String nomeUsuario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produtoId;

    private String nomeProduto;

    private Integer quantidade;

    private Integer estoqueAtual;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

    private Integer tipoMovimentacao; // 1 - Entrada, 2 - Saída

    private LocalDateTime dataPedido = LocalDateTime.now();
}
