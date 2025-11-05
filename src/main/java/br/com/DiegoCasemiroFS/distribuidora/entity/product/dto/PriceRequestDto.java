package br.com.DiegoCasemiroFS.distribuidora.entity.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal price;
}
