package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDtoInformation;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.OrderStatus;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

    OrderDtoInformation bringComplete(Long id);

    void updateStatus(Long id, OrderStatus orderStatus);
}
