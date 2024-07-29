package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private BigDecimal price;
}
