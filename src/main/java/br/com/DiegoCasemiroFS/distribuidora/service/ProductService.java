package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
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

  public List<Product> findAllProducts(){
    return productRepository.findAll();
  }

  public Product createProduct(Product product){
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product product){
    return productRepository.findById(id)
        .map(f -> {
          product.setId(f.getId());
          productRepository.save(product);
          return product;
        })
            .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }

  public void deleteProduct(Long id){
    productRepository.findById(id)
        .map(f -> {
          productRepository.delete(f);
          return Void.TYPE;
        })
            .orElseThrow(() -> new RuntimeException("Id não encontrado"));
  }
}