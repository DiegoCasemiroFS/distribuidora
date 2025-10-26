package br.com.DiegoCasemiroFS.distribuidora.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {

    private Long id;

    private String name;

    private ProductType productType;

    private BigDecimal price;

    private Integer quantity;
}
