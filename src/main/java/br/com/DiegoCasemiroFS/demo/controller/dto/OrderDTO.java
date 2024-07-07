package br.com.DiegoCasemiroFS.demo.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull(message = "o codigo do cliente deve ser informado.")
    private Long cliente;

    @NotNull(message = "o campo total deve ser informado.")
    private BigDecimal total;

    private List<ProductDTO> itens;
}
