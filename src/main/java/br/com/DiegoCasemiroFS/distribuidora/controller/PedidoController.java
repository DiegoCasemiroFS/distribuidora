package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findOrder")
    public Order findOrderById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Order> findAllOrders(){
        return orderService.findAll();
    }

    @PostMapping("/createOrder")
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @PutMapping("/updateOrder")
    public Order updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto){
        return orderService.updateOrder(id, orderRequestDto);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
