package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.ProductDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/findById/{id}")
  public Product findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @GetMapping("/findAll")
  public List<Product> findAll() {
    return productService.findAllProducts();
  }

  @GetMapping("/findBySupplier")
  public List<Product> findAllBySupplier(@PathVariable Long supplierId){
    return productService.findAllBySupplier(supplierId);
  }

  @PostMapping("/create")
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping("/update")
  public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
    return productService.updateProduct(id, productDto);
  }

  @DeleteMapping("/delete")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
