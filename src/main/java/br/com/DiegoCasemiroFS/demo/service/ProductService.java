package br.com.DiegoCasemiroFS.demo.service;

import br.com.DiegoCasemiroFS.demo.entity.Product;
import br.com.DiegoCasemiroFS.demo.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public Product findById(Long id){
    return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public List<Product> listProduto(){
    return productRepository.findAll();
  }

  public Product createProduto(Product product){
    productRepository.save(product);
    return product;
  }

  public Product updateProduto(Long id, Product product){
    return productRepository.findById(id)
        .map(f -> {
          product.setId(f.getId());
          productRepository.save(product);
          return product;
        }). orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deleteProduto(Long id){
    productRepository.findById(id)
        .map(f -> {
          productRepository.delete(f);
          return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

}
