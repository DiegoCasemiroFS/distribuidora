package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoRequestDto {

    private Long usuarioId;
    private Long produtoId;
    private Integer quantidade;
}
