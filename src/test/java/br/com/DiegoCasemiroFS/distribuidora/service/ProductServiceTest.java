package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProuctRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProuctRepository prouctRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(prouctRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(prouctRepository, times(1)).findById(1L);
    }

    @Test
    void findById_UserNotFound() {

        when(prouctRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
        verify(prouctRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        List<Product> products = List.of(product1, product2);
        when(prouctRepository.findAll()).thenReturn(products);
        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        assertEquals(1L, foundProducts.get(0).getId());
        assertEquals(2L, foundProducts.get(1).getId());
        verify(prouctRepository, times(1)).findAll();
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}