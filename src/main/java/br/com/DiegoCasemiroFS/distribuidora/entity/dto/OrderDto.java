package br.com.DiegoCasemiroFS.distribuidora.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long client;
    private BigDecimal total;
    private List<OrderItemDto> itens;
}
