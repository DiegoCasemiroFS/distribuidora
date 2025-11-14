package br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementRequestDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
