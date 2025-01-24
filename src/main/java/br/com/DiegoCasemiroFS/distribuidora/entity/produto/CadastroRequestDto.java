package br.com.DiegoCasemiroFS.distribuidora.entity.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequestDto {

    private Long id;

    private String nome;

    private TipoProduto tipoProduto;

    private BigDecimal preco;

    private Integer quantidade;

    private Integer estoque;
}
