package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDtoInformation;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.UpdateOrderDtoStatus;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.OrderStatus;
import br.com.DiegoCasemiroFS.distribuidora.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/order")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public Long createOrder(@RequestBody OrderDto orderDto){
    return orderService.createOrder(orderDto).getId();
  }

  @GetMapping
  public OrderDtoInformation bringComplete(@PathVariable Long id) {
    return orderService.bringComplete(id);
  }

  @PatchMapping
  public void updateStatus(@PathVariable Long id, @RequestBody UpdateOrderDtoStatus updateOrderDtoStatus){
    orderService.updateStatus(id, OrderStatus.valueOf(updateOrderDtoStatus.getNewStatus()));
  }
}
