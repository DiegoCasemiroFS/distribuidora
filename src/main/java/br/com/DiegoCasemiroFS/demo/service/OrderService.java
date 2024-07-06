package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  public Order findById(Long id){
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Order> listPedido(){
    return orderRepository.findAll();
  }

  public Order createPedido(Order order){
    //chamar produto
    orderRepository.save(order);
    return order;
  }

  public Order updatePedido(Long id, Order order){
    return orderRepository.findById(id)
        .map(f -> {
          order.setId(f.getId());
          return order;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deletePedido(Long id){
    orderRepository.findById(id)
        .map(f -> {
          orderRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}
