package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/findOrder")
    public Order findOrderById(@PathVariable Long id){
        return pedidoService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Order> findAllOrders(){
        return pedidoService.findAll();
    }

    @PostMapping("/createOrder")
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return pedidoService.createOrder(orderRequestDto);
    }

    @PutMapping("/updateOrder")
    public Order updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto){
        return pedidoService.updateOrder(id, orderRequestDto);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrderById(@PathVariable Long id){
        pedidoService.deleteOrder(id);
    }
}
