package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/pedido")
public class OrderController {

  @Autowired
  OrderService orderService;

  @GetMapping("/{id}")
  public Order findById(@PathVariable Long id){
    return orderService.findById(id);
  }

  @GetMapping
  public List<Order> findAllOrders(){
    return orderService.findAllOrders();
  }

  @PostMapping
  public Order createOrder(@RequestBody Order order){
    return orderService.createOrder(order);
  }

  @DeleteMapping
  public void deleteOrder(@PathVariable Long id){
    orderService.deleteOrder(id);
  }
}
