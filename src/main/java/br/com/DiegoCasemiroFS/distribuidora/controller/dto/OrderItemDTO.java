package br.com.DiegoCasemiroFS.distribuidora.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long productCode;
    private Integer quantity;
}
