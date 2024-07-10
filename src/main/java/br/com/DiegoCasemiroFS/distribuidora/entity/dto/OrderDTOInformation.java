package br.com.DiegoCasemiroFS.distribuidora.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOInformation {

    private Long id;
    private String cpf;
    private String name;
    private BigDecimal total;
    private String orderDate;
    private List<OrderDTOItemInformation> items;
}
