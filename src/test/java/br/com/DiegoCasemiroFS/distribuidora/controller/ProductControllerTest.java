package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private Product product;
    private ProductRequestDto productRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product = new Product();
        product.setId(1L);
        product.setName("Product Name");
        product.setPrice(BigDecimal.valueOf(100.0));

        productRequestDto = new ProductRequestDto();
        productRequestDto.setPrice(BigDecimal.valueOf(150.0));
    }

    @Test
    void testFindProductById() throws Exception {
        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/product/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.price").value(100.0));

        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testFindAllProducts() throws Exception {
        List<Product> productList = Collections.singletonList(product);
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Product Name"))
                .andExpect(jsonPath("$[0].price").value(100.0));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testCreateProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/createProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product Name\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.price").value(100.0));

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any(ProductRequestDto.class))).thenReturn(product);

        mockMvc.perform(put("/product/updateProduct/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\":150.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.price").value(100.0));

        verify(productService, times(1)).updateProduct(eq(1L), any(ProductRequestDto.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/product/deleteProduct/1"))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(1L);
    }
}