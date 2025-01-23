package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoResponseDto {

    private String nomeUsuario;
    private String nomeProduto;
    private Integer quantidade;
    private LocalDate dataVenda;
}
