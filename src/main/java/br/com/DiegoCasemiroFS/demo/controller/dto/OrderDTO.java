package br.com.DiegoCasemiroFS.demo.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long client;

    private BigDecimal total;

    private List<ProductDTO> itens;
}
