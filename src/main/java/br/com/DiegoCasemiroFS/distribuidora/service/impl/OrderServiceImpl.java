package br.com.DiegoCasemiroFS.distribuidora.service.impl;

import br.com.DiegoCasemiroFS.distribuidora.entity.Client;
import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.OrderItem;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDtoInformation;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.OrderDtoItemInformation;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.OrderStatus;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderItemRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderRepository;
import br.com.DiegoCasemiroFS.distribuidora.service.ClientService;
import br.com.DiegoCasemiroFS.distribuidora.service.OrderService;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ClientService clientService;
  private final ProductService productService;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Override
  public Order createOrder(OrderDto orderDto) {
    Client client = clientService.findById(orderDto.getClient());
    Order order = buildOrder(orderDto, client);
    List<OrderItem> orderItens = buildOrderItens(orderDto, order);
    orderRepository.save(order);
    orderItemRepository.saveAll(orderItens);
    return order;
  }

  @Override
  public OrderDtoInformation bringComplete(Long id) {
    Optional<Order> orderRepositoryByIdFetchItens = orderRepository.findByIdFetchItens(id);
    return orderRepositoryByIdFetchItens.map(
            m -> buildOrderDtoInformation(m)
    )
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
  }

  @Override
  public void updateStatus(Long id, OrderStatus orderStatus) {
    orderRepository.findById(id)
            .map(order -> {
              order.setStatus(orderStatus);
              return orderRepository.save(order);
            })
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

  }

  private static Order buildOrder (OrderDto orderDto, Client client){
    return Order.builder()
            .client(client)
            .orderDate(LocalDate.now())
            .total(orderDto.getTotal())
            .status(OrderStatus.REALIZADO)
            .build();
  }

  private List<OrderItem> buildOrderItens(OrderDto orderDto, Order order) {
    List<OrderItem> orderItens = orderDto.getItens()
            .stream()
            .map(
                    m -> {
                      Long productId = m.getProductCode();
                      Product product = productService.findById(productId);
                      return OrderItem.builder()
                              .order(order)
                              .product(product)
                              .quantity(m.getQuantity())
                              .build();
                    })
            .collect(Collectors.toList());
    return orderItens;
  }

  private OrderDtoInformation buildOrderDtoInformation(Order order) {
    return OrderDtoInformation.builder()
            .id(order.getId())
            .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .name(order.getClient().getName())
            .total(order.getTotal())
            .status(order.getStatus().name())
            .itens(buildOrderDtoItemInformation(order.getItens()))
            .build();
  }

  private List<OrderDtoItemInformation> buildOrderDtoItemInformation(List<OrderItem> itens) {
    if(CollectionUtils.isEmpty(itens)){
      return Collections.emptyList();
    }

    return itens.stream()
            .map(m -> OrderDtoItemInformation.builder()
                            .description(m.getProduct().getName())
                            .price(m.getProduct().getPrice())
                            .quantity(m.getQuantity())
                            .build()
                    )
            .collect(Collectors.toList());
  }
}