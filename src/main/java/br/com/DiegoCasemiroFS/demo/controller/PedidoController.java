package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Pedido;
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

  @GetMapping
  public Pedido findById(@PathVariable Long id){
    return pedidoService.findById(id);
  }

  @GetMapping
  public List<Pedido> listPedido(){
    return pedidoService.listPedido();
  }

  @PostMapping
  public Pedido createPedido(@RequestBody Pedido pedido){
    return pedidoService.createPedido(pedido);
  }

  @PutMapping
  public Pedido updatePedido(@PathVariable Long id, @RequestBody Pedido pedido){
    return pedidoService.updatePedido(id, pedido);
  }

  @DeleteMapping
  public void deletePedido(@PathVariable Long id){
    pedidoService.deletePedido(id);
  }
}
