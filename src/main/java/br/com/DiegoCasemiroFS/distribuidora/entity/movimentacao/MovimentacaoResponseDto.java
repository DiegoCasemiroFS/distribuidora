package br.com.DiegoCasemiroFS.distribuidora.entity.movimentacao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoResponseDto {

    private String nomeUsuario;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private LocalDate dataVenda;
}
