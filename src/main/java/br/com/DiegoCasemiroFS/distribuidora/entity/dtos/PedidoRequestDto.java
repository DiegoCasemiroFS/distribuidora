package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.Getter;

@Getter
public class PedidoRequestDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
