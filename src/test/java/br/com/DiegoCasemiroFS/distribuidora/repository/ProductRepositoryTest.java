package br.com.DiegoCasemiroFS.distribuidora.repository;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.enums.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Product Name");
        product.setBrand("Brand Name");
        product.setProductType(ProductType.OLEO_ESSENCIAL);
        product.setPrice(BigDecimal.valueOf(100.0));
        entityManager.persistAndFlush(product);
    }

    @Test
    void testFindById() {
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo(product.getName());
    }

    @Test
    void testSaveProduct() {
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setPrice(BigDecimal.valueOf(200.0));
        Product savedProduct = productRepository.save(newProduct);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("New Product");
    }

    @Test
    void testDeleteProduct() {
        productRepository.delete(product);
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertThat(deletedProduct).isNotPresent();
    }
}