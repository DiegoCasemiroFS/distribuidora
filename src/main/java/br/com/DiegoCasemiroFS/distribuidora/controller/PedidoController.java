package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Pedido;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.PedidoRequestDto;
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
    public Pedido findOrderById(@PathVariable Long id){
        return pedidoService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Pedido> findAllOrders(){
        return pedidoService.findAll();
    }

    @PostMapping("/createOrder")
    public Pedido createOrder(@RequestBody PedidoRequestDto pedidoRequestDto){
        return pedidoService.createOrder(pedidoRequestDto);
    }

    @PutMapping("/updateOrder")
    public Pedido updateOrder(@PathVariable Long id, @RequestBody PedidoRequestDto pedidoRequestDto){
        return pedidoService.updateOrder(id, pedidoRequestDto);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrderById(@PathVariable Long id){
        pedidoService.deleteOrder(id);
    }
}
