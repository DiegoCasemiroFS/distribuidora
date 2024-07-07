package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.controller.dto.OrderDTO;
import br.com.DiegoCasemiroFS.demo.entity.Client;
import br.com.DiegoCasemiroFS.demo.entity.Order;
import br.com.DiegoCasemiroFS.demo.entity.OrderItem;
import br.com.DiegoCasemiroFS.demo.entity.Product;
import br.com.DiegoCasemiroFS.demo.repository.OrderItemRepository;
import br.com.DiegoCasemiroFS.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrderItemRepository orderItemRepository;

  @Autowired
  ClientService clientService;

  @Autowired
  ProductService productService;

  public Order findById(Long id){
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Order> findAllOrders(){
    return orderRepository.findAll();
  }

  public Order createOrder(OrderDTO orderDTO){
    Client client = clientService.findById(orderDTO.getCliente());
    Order order = buildOrder(orderDTO, client);
    List<OrderItem> orderItens = buildOrderItens(orderDTO, order);
    orderRepository.save(order);
    orderItemRepository.saveAll(orderItens);
    return order;
  }

  private List<OrderItem> buildOrderItens(OrderDTO orderDTO, Order order) {
    List<OrderItem> orderItens = orderDTO.getItens()
            .stream()
            .map(m -> {
              Long productId = m.getProductCode();
              Product product = productService.findById(productId);
              return OrderItem.builder()
                      .order(order)
                      .product(product)
                      .quantity(m.getStockQuatity())
                      .build();
            }).collect(Collectors.toList());
    return orderItens;
  }

  private static Order buildOrder(OrderDTO orderDTO, Client client) {
    return Order.builder()
            .client(client)
            .orderDate(LocalDate.now())
            .totalValue(orderDTO.getTotal())
            .build();
  }

  public void deleteOrder(Long id){
    orderRepository.findById(id)
        .map(f -> {
          orderRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}
