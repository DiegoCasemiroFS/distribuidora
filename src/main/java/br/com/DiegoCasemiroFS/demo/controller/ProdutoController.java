package br.com.DiegoCasemiroFS.demo.controller;

import br.com.DiegoCasemiroFS.demo.entity.Produto;
import br.com.DiegoCasemiroFS.demo.service.ProdutoService;
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
public class ProdutoController {

  @Autowired
  ProdutoService produtoService;

  @GetMapping
  public Produto findById(Long id) {
    return produtoService.findById(id);
  }

  @GetMapping
  public List<Produto> findAll() {
    return produtoService.listProduct();
  }

  @PostMapping
  public Produto createProduct(@RequestBody Produto produto) {
    return produtoService.createProduct(produto);
  }

  @PutMapping
  public Produto updateProduct(@PathVariable Long id, @RequestBody Produto produto) {
    return produtoService.updateProduct(id, produto);
  }

  @DeleteMapping
  public void deleteProduct(@PathVariable Long id) {
    produtoService.deleteProduct(id);
  }
}
