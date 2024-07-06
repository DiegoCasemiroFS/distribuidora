package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Product;
import br.com.DiegoCasemiroFS.demo.service.ProductService;
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
@RequestMapping("v1/api/produto")
public class ProdutoController {

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public Product findById(Long id) {
    return productService.findById(id);
  }

  @GetMapping
  public List<Product> findAll() {
    return productService.listProduto();
  }

  @PostMapping
  public Product createProduto(@RequestBody Product product) {
    return productService.createProduto(product);
  }

  @PutMapping
  public Product updateProduto(@PathVariable Long id, @RequestBody Product product) {
    return productService.updateProduto(id, product);
  }

  @DeleteMapping
  public void deleteProduto(@PathVariable Long id) {
    productService.deleteProduto(id);
  }
}
