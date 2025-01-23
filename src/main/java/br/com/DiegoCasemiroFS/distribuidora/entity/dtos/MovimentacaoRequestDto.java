package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoRequestDto {

    private Long usuarioId;
    private Long produtoId;
    private Integer quantidade;
}
