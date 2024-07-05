package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Product;
import br.com.DiegoCasemiroFS.demo.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  @Autowired
  ProdutoRepository produtoRepository;

  public Product findById(Long id){
    return produtoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Product> listProduto(){
    return produtoRepository.findAll();
  }

  public Product createProduto(Product product){
    produtoRepository.save(product);
    return product;
  }

  public Product updateProduto(Long id, Product product){
    return produtoRepository.findById(id)
        .map(f -> {
          product.setId(f.getId());
          produtoRepository.save(product);
          return product;
        }). orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deleteProduto(Long id){
    produtoRepository.findById(id)
        .map(f -> {
          produtoRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

}
