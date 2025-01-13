package br.com.DiegoCasemiroFS.distribuidora.repository;

import br.com.DiegoCasemiroFS.distribuidora.entity.Orders;
import br.com.DiegoCasemiroFS.distribuidora.entity.Users;
import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Orders order;
    private Users user;
    private Product product;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setName("User Name");
        user.setEmail("user@example.com");
        user.setAddress("123 Main St");
        user.setPhone("123-456-7890");
        entityManager.persistAndFlush(user);

        product = new Product();
        product.setName("Product Name");
        product.setBrand("Brand Name");
        product.setProductType(ProductType.CAPSULA);
        product.setPrice(BigDecimal.valueOf(100.0));
        entityManager.persistAndFlush(product);

        order = new Orders();
        order.setUsersId(user);
        order.setProductId(product);
        order.setQuantity(2);
        order.setOrderDate(LocalDate.now());
        entityManager.persistAndFlush(order);
    }

    @Test
    void testFindById() {
        Optional<Orders> foundOrder = orderRepository.findById(order.getId());
        assertThat(foundOrder).isPresent();
        assertThat(foundOrder.get().getQuantity()).isEqualTo(order.getQuantity());
    }

    @Test
    void testSaveOrder() {
        Orders newOrder = new Orders();
        newOrder.setUsersId(user);
        newOrder.setProductId(product);
        newOrder.setQuantity(3);
        newOrder.setOrderDate(LocalDate.now());
        Orders savedOrder = orderRepository.save(newOrder);
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getQuantity()).isEqualTo(3);
    }

    @Test
    void testDeleteOrder() {
        orderRepository.delete(order);
        Optional<Orders> deletedOrder = orderRepository.findById(order.getId());
        assertThat(deletedOrder).isNotPresent();
    }
}