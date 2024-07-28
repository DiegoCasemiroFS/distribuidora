package br.com.DiegoCasemiroFS.distribuidora.entity.dto;

import br.com.DiegoCasemiroFS.distribuidora.entity.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter public class OrderDto {

    private Long clientId;

    private Long supplierId;

    private Long productId;

    private Integer quantity;

    private LocalDate orderDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
