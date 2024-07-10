package br.com.DiegoCasemiroFS.distribuidora.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long client;

    private BigDecimal total;

    private List<Long> itens;
}
