package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.controller.dto.OrderDTO;
import br.com.DiegoCasemiroFS.distribuidora.controller.dto.OrderDTOInformation;
import br.com.DiegoCasemiroFS.distribuidora.controller.dto.OrderDTOItemInformation;
import br.com.DiegoCasemiroFS.distribuidora.entity.Client;
import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.OrderItem;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderItemRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

  public Order createOrder(OrderDTO orderDTO){
    Client client = clientService.findById(orderDTO.getClient());
    Order order = buildOrder(orderDTO, client);
    List<OrderItem> orderItens = buildOrderItens(orderDTO, order);
    orderRepository.save(order);
    orderItemRepository.saveAll(orderItens);
    return order;
  }

  public OrderDTOInformation bringComplete(Long id){
    Optional<Order> orderRepositoryByIdFetchItens = orderRepository.findByIdFetchItens(id);
    return orderRepositoryByIdFetchItens.map(
            m -> buildOrderDTOInformation(m)
    ).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
  }

  private static Order buildOrder(OrderDTO orderDTO, Client client) {
    return Order.builder()
            .client(client)
            .orderDate(LocalDate.now())
            .totalValue(orderDTO.getTotal())
            .build();
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

  private OrderDTOInformation buildOrderDTOInformation(Order order){
    return OrderDTOInformation.builder()
            .id(order.getId())
            .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(order.getClient().getCpf())
            .name(order.getClient().getName())
            .total(order.getTotalValue())
            .items(buildOrderDTOItemInformation(order.getItems()))
            .build();
  }

  private List<OrderDTOItemInformation> buildOrderDTOItemInformation(List<OrderItem> itens){
    if (CollectionUtils.isEmpty(itens)) {
      return Collections.emptyList();
    }

    return itens.stream()
            .map(m -> OrderDTOItemInformation.builder()
                    .group(m.getProduct().getGroup())
                    .price(m.getProduct().getPrice())
                    .quantity(m.getQuantity())
                    .build()
            )
            .collect(Collectors.toList());
  }

  public void deleteOrder(Long id){
    orderRepository.findById(id)
        .map(f -> {
          orderRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não econtrado"));
  }

}
