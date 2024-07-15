package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping
  public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
    return productService.updateProduct(id, product);
  }

  @DeleteMapping
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
