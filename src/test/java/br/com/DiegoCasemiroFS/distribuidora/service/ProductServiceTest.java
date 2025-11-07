package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.CreateRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.PriceRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.StockRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.InsufficientStockException;
import br.com.DiegoCasemiroFS.distribuidora.exception.InsufficientValueException;
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

import java.math.BigDecimal;
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
    @DisplayName("Deve retornar o produto quando o ID for válido")
    void shouldReturnProductWhenIdIsValid(){
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product resultado = productService.findById(id);

        assertNotNull(resultado);
        assertEquals(resultado, product);
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar ProductNotFoundException quando o ID for inválido")
    void shouldThrowProductNotFoundWhenIdIsInvalid(){
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(id));
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar ProductAlreadyExistsException quando o produto já existir")
    void shouldThrowProductAlreadyExistsWhenProductExists(){

        when(productRepository.existsByNameIgnoreCase(createRequestDto.getName())).thenReturn(true);

        assertThrows(ProductAlreadyExistsException.class, () -> productService.createProduct(createRequestDto));
        verify(productRepository).existsByNameIgnoreCase(createRequestDto.getName());
    }

    @Test
    @DisplayName("Deve retornar o produto criado quando o produto não existir")
    void shouldCreateProductWhenProductDoesNotExist(){

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
    @DisplayName("Deve retornar o produto com preço atualizado quando ID e price forem válidos")
    void shouldUpdatePriceWhenIdAndPriceAreValid(){

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

    @Test
    @DisplayName("Deve retornar ProductNotFoundException quando o ID for inválido")
    void shouldThrowProductNotFoundWhenFindingByIdThatDoesNotExist(){

        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findById(id));
        verify(productRepository).findById(id);

    }

    @Test
    @DisplayName("Deve retornar RuntimeException quando price for zero")
    void shouldThrowWhenPriceIsZero(){

        Long id = 1L;
        PriceRequestDto dto = new PriceRequestDto(BigDecimal.ZERO);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> productService.updatePrice(id, dto));
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar RuntimeException quando price for negativo")
    void shouldThrowWhenPriceIsNegative(){

        Long id = 1L;
        PriceRequestDto dto = new PriceRequestDto(BigDecimal.valueOf(-5.00));

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> productService.updatePrice(id, dto));
        verify(productRepository).findById(id);
    }

        @Test
    @DisplayName("Deve adicionar estoque quando o id e o valor forem válidos")
    void shouldAddStockWhenIdAndValueAreValid() {

        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(15);

        when(product.getStock()).thenReturn(10);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.addStock(id, dto);

        verify(productRepository).findById(id);
        verify(product).setStock(25);
        verify(productRepository).save(productCaptor.capture());
        Product newProduct = productCaptor.getValue();

        assertNotNull(newProduct);
    }

    @Test
    @DisplayName("Deve retornar ProductNotFoundException quando o id for inválido ao adicionar estoque")
    void shouldThrowProductNotFoundWhenAddingStockWithInvalidId() {
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(15);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.addStock(id, dto));
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar InsufficientValueException quando o valor for inválido ao adicionar estoque")
    void shouldThrowInsufficientValueWhenAddingStockWithInvalidValue() {
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(-15);

        assertThrows(InsufficientValueException.class, () -> productService.addStock(id, dto));
    }

    @Test
    @DisplayName("Deve remover estoque quando o id e o valor forem válidos")
    void shouldRemoveStockWhenIdAndValueAreValid() {
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(5);

        when(product.getStock()).thenReturn(10);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.removeStock(id, dto);

        verify(productRepository).findById(id);
        verify(product).setStock(5);
        verify(productRepository).save(productCaptor.capture());
        Product newProduct = productCaptor.getValue();

        assertNotNull(newProduct);
    }

    @Test
    @DisplayName("Deve retornar ProductNotFoundException quando o id for inválido ao remover estoque")
    void shouldThrowProductNotFoundWhenRemovingStockWithInvalidId() {
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(15);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.removeStock(id, dto));
        verify(productRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar InsufficientValueException quando o valor for inválido ao remover estoque")
    void shouldThrowInsufficientValueWhenRemovingStockWithInvalidValue() {
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(-15);

        assertThrows(InsufficientValueException.class, () -> productService.addStock(id, dto));
    }

    @Test
    @DisplayName("Deve retornar InsufficientStockException quando quantity for maior que o Estoque atual")
    void shouldThrowInsufficientStockWhenRemovingMoreThanAvailable(){
        Long id = 1L;
        StockRequestDto dto = new StockRequestDto(15);

        when(product.getStock()).thenReturn(10);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(InsufficientStockException.class, () -> productService.removeStock(id, dto));
    }
}