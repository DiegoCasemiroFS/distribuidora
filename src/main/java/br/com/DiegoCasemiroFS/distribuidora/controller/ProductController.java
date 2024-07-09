package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public Product findById(Long id) {
    return productService.findById(id);
  }

  @GetMapping
  public List<Product> findAll() {
    return productService.findAllProducts();
  }

  @PostMapping
  public Product createProduto(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping
  public Product updateProduto(@PathVariable Long id, @RequestBody Product product) {
    return productService.updateProduct(id, product);
  }

  @DeleteMapping
  public void deleteProduto(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
