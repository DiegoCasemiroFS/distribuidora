package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.CreateRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.PriceRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductAlreadyExistsException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;

    @Mock
    private CreateRequestDto createRequestDto;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Mock
    private PriceRequestDto priceRequestDto;

    @Test
    @DisplayName("Verifica se o Produto tem um ID válido")
    void teste01(){
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product resultado = productService.findById(id);

        assertNotNull(resultado);
        assertEquals(resultado, product);
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Retorna exception para um ID inválido")
    void teste02(){
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(id));
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Retorna exception ao tentar criar um Produto que já existe")
    void teste03(){

        when(productRepository.existsByNameIgnoreCase(createRequestDto.getName())).thenReturn(true);

        assertThrows(ProductAlreadyExistsException.class, () -> productService.createProduct(createRequestDto));
        verify(productRepository).existsByNameIgnoreCase(createRequestDto.getName());
    }

    @Test
    @DisplayName("Cria um Produto se ele não existir")
    void teste05(){

        when(productRepository.existsByNameIgnoreCase(createRequestDto.getName())).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.createProduct(createRequestDto);

        verify(productRepository).save(productCaptor.capture());
        Product newProduct = productCaptor.getValue();

        assertNotNull(newProduct);
        assertEquals(newProduct.getName(), createRequestDto.getName());
        verify(productRepository).existsByNameIgnoreCase(createRequestDto.getName());
    }

    @Test
    @DisplayName("Atualiza o preço se o ID e o valor forem válidos")
    void teste06(){

        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.updatePrice(id, priceRequestDto);

        verify(productRepository).findById(id);
        verify(productRepository).save(productCaptor.capture());
        Product newProduct = productCaptor.getValue();

        assertNotNull(newProduct);
        assertEquals(product.getId(), newProduct.getId());
    }

    //retorna exception se o id for invalido

    //retorna exception se o valor for invalido

    //adiciona estoque se o id e o valor forem validos

    //retorna exception se o id for invalido

    //retorna exception se o valor for invalido

    //remove estoque se o id e o valor forem validos

    //retorna exception se o id for invalido

    //retorna exception se o valor for invalido
}