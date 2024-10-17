package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Orders;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.OrderNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.OrderRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;
    private Orders orders;
    private Users user;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Users();
        user.setId(1L);

        product = new Product();
        product.setId(1L);

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1L);
        orderRequestDto.setProductId(1L);
        orderRequestDto.setQuantity(3);
    }


    @Test
    void findById_OrderFound() {

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));

        Orders orderFound = orderService.findById(1L);

        assertNotNull(orderFound);
        assertEquals(1L, orderFound.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void findById_OrderNotFound(){

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.findById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {

        Orders orders1 = new Orders();
        orders1.setId(1L);

        Orders orders2 = new Orders();
        orders2.setId(2L);

        List<Orders> orders = List.of(orders1, orders2);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Orders> ordersFounded = orderService.findAll();

        assertNotNull(ordersFounded);
        assertEquals(2, ordersFounded.size());
        assertEquals(1L, ordersFounded.get(0).getId());
        assertEquals(2L, ordersFounded.get(1).getId());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void createOrder_Successfully() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Orders createdOrder = orderService.createOrder(orderRequestDto);

        assertNotNull(createdOrder);
        assertEquals(user, createdOrder.getUsersId());
        assertEquals(product, createdOrder.getProductId());
        assertEquals(3, createdOrder.getQuantity());

        verify(userRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void createOrder_UserNotFound(){

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> orderService.createOrder(orderRequestDto));

        verify(userRepository, times(1)).findById(1L);
        verify(productRepository, never()).findById(anyLong());
    }

    @Test
    public void testCreateOrder_ProductNotFound() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void deleteOrder() {
    }
}