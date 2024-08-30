package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Order;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.OrderNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Order findById(Long id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequestDto orderRequestDto){
        User user = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        Order order = new Order();
        order.setUserId(user);
        order.setProductId(product);
        order.setQuantity(orderRequestDto.getQuantity());

        return order;
    }

    public Order updateOrder(Long id, OrderRequestDto orderRequestDto){
        User user = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        return orderRepository.findById(id)
                .map(order -> {
                    order.setUserId(user);
                    order.setProductId(product);
                    order.setQuantity(orderRequestDto.getQuantity());
                    return order;
                }).orElseThrow(OrderNotFoundException::new);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
