package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Orders;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.OrderRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;
    private Orders order;
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        order = new Orders();
        order.setId(1L);
        order.setQuantity(10);

        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1L);
        orderRequestDto.setProductId(1L);
        orderRequestDto.setQuantity(10);
    }

    @Test
    void testFindOrderById() throws Exception {
        when(orderService.findById(1L)).thenReturn(order);

        mockMvc.perform(get("/order/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(orderService, times(1)).findById(1L);
    }

    @Test
    void testFindAllOrders() throws Exception {
        List<Orders> ordersList = Collections.singletonList(order);
        when(orderService.findAll()).thenReturn(ordersList);

        mockMvc.perform(get("/order/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].quantity").value(10));

        verify(orderService, times(1)).findAll();
    }

    @Test
    void testCreateOrder() throws Exception {
        when(orderService.createOrder(any(OrderRequestDto.class))).thenReturn(order);

        mockMvc.perform(post("/order/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"productId\":1,\"quantity\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(orderService, times(1)).createOrder(any(OrderRequestDto.class));
    }

    @Test
    void testUpdateOrder() throws Exception {
        when(orderService.updateOrder(eq(1L), any(OrderRequestDto.class))).thenReturn(order);

        mockMvc.perform(put("/order/updateOrder/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"productId\":1,\"quantity\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(orderService, times(1)).updateOrder(eq(1L), any(OrderRequestDto.class));
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/order/deleteOrder/1"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteOrder(1L);
    }
}