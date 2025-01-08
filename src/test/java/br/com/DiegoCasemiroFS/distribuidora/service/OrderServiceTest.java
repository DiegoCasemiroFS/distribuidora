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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;
    private Users user;
    private Product product;
    private Orders order;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(1L);
        product = new Product();
        product.setId(1L);
        order = new Orders();
        order.setId(1L);
        order.setUsersId(user);
        order.setProductId(product);
        order.setQuantity(10);

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(user.getId());
        orderRequestDto.setProductId(product.getId());
        orderRequestDto.setQuantity(10);
    }

    @Test
    void testFindById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Orders foundOrder = orderService.findById(1L);

        assertNotNull(foundOrder);
        assertEquals(order.getId(), foundOrder.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.findById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateOrder() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Orders.class))).thenReturn(order);

        Orders createdOrder = orderService.createOrder(orderRequestDto);

        assertNotNull(createdOrder);
        assertEquals(order.getId(), createdOrder.getId());
        verify(userRepository, times(1)).findById(user.getId());
        verify(productRepository, times(1)).findById(product.getId());
        verify(orderRepository, times(1)).save(any(Orders.class));
    }

    @Test
    void testCreateOrder_UserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> orderService.createOrder(orderRequestDto));
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testCreateOrder_ProductNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> orderService.createOrder(orderRequestDto));
        verify(userRepository, times(1)).findById(user.getId());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testUpdateOrder() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Orders.class))).thenReturn(order);

        Orders updatedOrder = orderService.updateOrder(order.getId(), orderRequestDto);

        assertNotNull(updatedOrder);
        assertEquals(order.getId(), updatedOrder.getId());
        verify(userRepository, times(1)).findById(user.getId());
        verify(productRepository, times(1)).findById(product.getId());
        verify(orderRepository, times(1)).findById(order.getId());
        verify(orderRepository, times(1)).save(any(Orders.class));
    }

    @Test
    void testUpdateOrder_NotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(order.getId(), orderRequestDto));

        verify(orderRepository, times(1)).findById(order.getId());
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(order.getId());

        orderService.deleteOrder(order.getId());

        verify(orderRepository, times(1)).deleteById(order.getId());
    }
}