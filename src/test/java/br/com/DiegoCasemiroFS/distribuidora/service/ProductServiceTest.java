package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.ProductType;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

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

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findById_UserNotFound() {

        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.findById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        List<Product> products = List.of(product1, product2);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        assertEquals(1L, foundProducts.get(0).getId());
        assertEquals(2L, foundProducts.get(1).getId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Melaleuca");
        product.setBrand("Evergreen");
        product.setProductType(ProductType.OLEO_ESSENCIAL);
        product.setPrice(new BigDecimal("49.99"));

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals(product, result);
        verify(productRepository).save(product);
    }

    @Test
    void updateProductSuccessfuly() {

        Long productId = 1L;
        Product existingProduct = new Product();

        existingProduct.setId(productId);
        existingProduct.setPrice(new BigDecimal("100.00"));

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setPrice(new BigDecimal("120.00"));

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        Product updateProduct = productService.updateProduct(productId, productRequestDto);

        assertEquals(productRequestDto.getPrice(), updateProduct.getPrice());
        verify(productRepository).findById(productId);
    }

    @Test
    void updateProductNotSuccessfuly() {

        Long productId = 1L;
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setPrice(new BigDecimal("120.00"));

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productId, productRequestDto));
        verify(productRepository).findById(productId);
    }

    @Test
    void deleteProduct() {
    }
}