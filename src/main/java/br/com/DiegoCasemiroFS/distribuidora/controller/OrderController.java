package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Orders;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findById/{id}")
    public Orders findOrderById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Orders> findAllOrders(){
        return orderService.findAll();
    }

    @PostMapping("/createOrder")
    public Orders createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @PutMapping("/updateOrder/{id}")
    public Orders updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto){
        return orderService.updateOrder(id, orderRequestDto);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
