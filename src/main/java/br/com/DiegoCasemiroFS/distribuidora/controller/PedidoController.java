package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Pedido;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.PedidoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    public Pedido findOrderById(@PathVariable Long id){
        return pedidoService.findById(id);
    }

    public List<Pedido> findAllOrders(){
        return pedidoService.findAll();
    }

    public Pedido createOrder(@RequestBody PedidoRequestDto pedidoRequestDto){
        return pedidoService.createOrder(pedidoRequestDto);
    }

    public Pedido updateOrder(@PathVariable Long id, @RequestBody PedidoRequestDto pedidoRequestDto){
        return pedidoService.updateOrder(id, pedidoRequestDto);
    }

    public void deleteOrderById(@PathVariable Long id){
        pedidoService.deleteOrder(id);
    }
}
