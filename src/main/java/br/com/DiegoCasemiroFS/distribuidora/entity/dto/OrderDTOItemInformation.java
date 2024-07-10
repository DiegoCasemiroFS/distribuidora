package br.com.DiegoCasemiroFS.distribuidora.entity.dto;

import br.com.DiegoCasemiroFS.distribuidora.entity.enums.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOItemInformation {

    private Group group;

    private BigDecimal price;

    private Integer quantity;
}
