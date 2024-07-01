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
@RequestMapping("v1/api/produto")
public class ProdutoController {

  @Autowired
  ProdutoService produtoService;

  @GetMapping("/{id}")
  public Produto findById(Long id) {
    return produtoService.findById(id);
  }

  @GetMapping
  public List<Produto> findAll() {
    return produtoService.listProduto();
  }

  @PostMapping
  public Produto createProduto(@RequestBody Produto produto) {
    return produtoService.createProduto(produto);
  }

  @PutMapping
  public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
    return produtoService.updateProduto(id, produto);
  }

  @DeleteMapping
  public void deleteProduto(@PathVariable Long id) {
    produtoService.deleteProduto(id);
  }
}
