package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  @Autowired
  PedidoRepository pedidoRepository;

  public Order findById(Long id){
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Order> listPedido(){
    return pedidoRepository.findAll();
  }

  public Order createPedido(Order order){
    //chamar produto
    pedidoRepository.save(order);
    return order;
  }

  public Order updatePedido(Long id, Order order){
    return pedidoRepository.findById(id)
        .map(f -> {
          order.setId(f.getId());
          return order;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deletePedido(Long id){
    pedidoRepository.findById(id)
        .map(f -> {
          pedidoRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}
