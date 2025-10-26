package br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementResponseDto {

    private String userName;
    private String productName;
    private Integer quantity;
    private BigDecimal totalValue;
    private LocalDate orderDate;
}
