package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
