package br.com.DiegoCasemiroFS.distribuidora.entity.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDto {

    @NotBlank
    @Min(0)
    private Integer quantity;
}
