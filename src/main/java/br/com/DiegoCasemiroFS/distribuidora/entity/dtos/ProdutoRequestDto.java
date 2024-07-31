package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDto {

    private BigDecimal price;
}
