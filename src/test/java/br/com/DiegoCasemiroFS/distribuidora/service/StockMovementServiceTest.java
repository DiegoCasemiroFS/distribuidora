package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.enums.ProductType;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.StockMovement;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.StockMovementNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.StockMovementRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {

    @InjectMocks
    private StockMovementService stockMovementService;

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private StockMovement stockMovement;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("Deve retornar um movimento de estoque quando o ID for válido")
    void shouldReturnStockMovementWhenIdIsValid(){
        Long id = 1L;

        when(stockMovementRepository.findById(id)).thenReturn(Optional.of(stockMovement));

        StockMovement sm = stockMovementService.findById(id);

        assertNotNull(sm);
        assertEquals(sm, stockMovement);
        verify(stockMovementRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar StockMovementNotFoundException quando o ID for inválido")
    void shouldThrowStockMovementNotFoundWhenIdIsInvalid(){
        Long id = 1L;

        when(stockMovementRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(StockMovementNotFoundException.class, () -> stockMovementService.findById(id));
        verify(stockMovementRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar uma venda caso o produto e o usuário sejam validos")
    void shouldReturnSaleWhenProductAndUserAreValid(){

        Long userId = 1L;
        Long productId = 1L;

        Product p = new Product();
        p.setId(productId);
        p.setName("nome");
        p.setProductType(ProductType.OLEO_ESSENCIAL);
        p.setPrice(BigDecimal.valueOf(49.99));
        p.setStock(15);

        StockMovementRequestDto dto = new StockMovementRequestDto();
        dto.setUserId(userId);
        dto.setProductId(productId);
        dto.setQuantity(10);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(p));

        StockMovementResponseDto response = stockMovementService.saleToCostumer(dto);

        assertNotNull(response);
        verify(userRepository).findById(userId);
        verify(productRepository).findById(productId);
        verify(productRepository).save(p);
        assertEquals(5, p.getStock());
    }

    @Test
    @DisplayName("Deve retornar UserNotFoundException caso o usuário seja inválido")
    void shouldThrowExceptionWhenUserIsNotValid(){

        Long userId = 1L;
        Long productId = 1L;

        Product p = new Product();
        p.setId(productId);
        p.setName("nome");
        p.setProductType(ProductType.OLEO_ESSENCIAL);
        p.setPrice(BigDecimal.valueOf(49.99));
        p.setStock(15);

        StockMovementRequestDto dto = new StockMovementRequestDto();
        dto.setUserId(userId);
        dto.setProductId(productId);
        dto.setQuantity(10);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> stockMovementService.saleToCostumer(dto));
        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("Deve retornar ProductNotFoundException caso o produto seja inválido")
    void shouldThrowExceptionWhenProductIsNotValid(){

        Long userId = 1L;
        Long productId = 1L;

        Product p = new Product();
        p.setId(productId);
        p.setName("nome");
        p.setProductType(ProductType.OLEO_ESSENCIAL);
        p.setPrice(BigDecimal.valueOf(49.99));
        p.setStock(15);

        StockMovementRequestDto dto = new StockMovementRequestDto();
        dto.setUserId(userId);
        dto.setProductId(productId);
        dto.setQuantity(10);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> stockMovementService.saleToCostumer(dto));
        verify(userRepository).findById(userId);
        verify(productRepository).findById(productId);
    }

}