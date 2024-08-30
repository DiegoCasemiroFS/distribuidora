package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
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
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Product product;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = produtoService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_UserNotFound() {

        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> produtoService.findById(1L));
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        List<Product> products = List.of(product1, product2);
        when(produtoRepository.findAll()).thenReturn(products);
        List<Product> foundProducts = produtoService.findAll();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        assertEquals(1L, foundProducts.get(0).getId());
        assertEquals(2L, foundProducts.get(1).getId());
        verify(produtoRepository, times(1)).findAll();
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