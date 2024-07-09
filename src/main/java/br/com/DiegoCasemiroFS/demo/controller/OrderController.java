package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.controller.dto.OrderDTO;
import br.com.DiegoCasemiroFS.demo.controller.dto.OrderDTOInformation;
import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.service.OrderService;
import br.com.DiegoCasemiroFS.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public Order findById(@PathVariable Long id){
    return orderService.findById(id);
  }

  @PostMapping
  public Long createOrder(@RequestBody OrderDTO order){
    return orderService.createOrder(order).getId();
  }

  @GetMapping
  public OrderDTOInformation bringComplete(@PathVariable Long id) {
    return orderService.bringComplete(id);
  }

  @DeleteMapping
  public void deleteOrder(@PathVariable Long id){
    orderService.deleteOrder(id);
  }
}
