package br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementRequestDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
