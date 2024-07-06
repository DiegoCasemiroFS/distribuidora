package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.entity.Product;
import br.com.DiegoCasemiroFS.demo.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import br.com.DiegoCasemiroFS.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  public Order findById(Long id){
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Order> findAllOrders(){
    return orderRepository.findAll();
  }

  public Order createOrder(Order order){
    orderRepository.save(order);
    return order;
  }

  public void deleteOrder(Long id){
    orderRepository.findById(id)
        .map(f -> {
          orderRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}
