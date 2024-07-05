package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/pedido")
public class PedidoController {

  @Autowired
  PedidoService pedidoService;

  @GetMapping("/{id}")
  public Order findById(@PathVariable Long id){
    return pedidoService.findById(id);
  }

  @GetMapping
  public List<Order> listPedido(){
    return pedidoService.listPedido();
  }

  @PostMapping
  public Order createPedido(@RequestBody Order order){
    return pedidoService.createPedido(order);
  }

  @PutMapping
  public Order updatePedido(@PathVariable Long id, @RequestBody Order order){
    return pedidoService.updatePedido(id, order);
  }

  @DeleteMapping
  public void deletePedido(@PathVariable Long id){
    pedidoService.deletePedido(id);
  }
}
