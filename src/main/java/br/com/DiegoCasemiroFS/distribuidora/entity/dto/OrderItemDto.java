package br.com.DiegoCasemiroFS.distribuidora.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productCode;
    private Integer quantity;
}
