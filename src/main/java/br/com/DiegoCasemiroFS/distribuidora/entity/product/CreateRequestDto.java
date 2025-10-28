package br.com.DiegoCasemiroFS.distribuidora.entity.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {

    @NotBlank
    private String name;

    @NotNull
    private ProductType productType;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;
}
