package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Orders;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
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

    public Orders findById(Long id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Orders> findAll(){
        return orderRepository.findAll();
    }

    public Orders createOrder(OrderRequestDto orderRequestDto){
        Users users = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        Orders orders = new Orders();
        orders.setUsersId(users);
        orders.setProductId(product);
        orders.setQuantity(orderRequestDto.getQuantity());

        return orders;
    }

    public Orders updateOrder(Long id, OrderRequestDto orderRequestDto){
        Users users = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        return orderRepository.findById(id)
                .map(orders -> {
                    orders.setUsersId(users);
                    orders.setProductId(product);
                    orders.setQuantity(orderRequestDto.getQuantity());
                    return orders;
                }).orElseThrow(OrderNotFoundException::new);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
