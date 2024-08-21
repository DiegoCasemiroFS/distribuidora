package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
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
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;

    @BeforeEach
    void setUp(){
        produto = new Produto();
        produto.setId(1L);
    }

    @Test
    void findById_UserFound() {

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto foundProduto = produtoService.findById(1L);

        assertNotNull(foundProduto);
        assertEquals(1L, foundProduto.getId());
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

        Produto produto1 = new Produto();
        produto1.setId(1L);

        Produto produto2 = new Produto();
        produto2.setId(2L);

        List<Produto> produtos = List.of(produto1, produto2);
        when(produtoRepository.findAll()).thenReturn(produtos);
        List<Produto> foundProdutos = produtoService.findAll();

        assertNotNull(foundProdutos);
        assertEquals(2, foundProdutos.size());
        assertEquals(1L, foundProdutos.get(0).getId());
        assertEquals(2L, foundProdutos.get(1).getId());
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