package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.StockMovement;
import br.com.DiegoCasemiroFS.distribuidora.exception.StockMovementNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.StockMovementRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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



}