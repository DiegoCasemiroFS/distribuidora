package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.Supplier;
import br.com.DiegoCasemiroFS.distribuidora.entity.dto.ProductDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.SupplierNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import br.com.DiegoCasemiroFS.distribuidora.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  SupplierRepository supplierRepository;

  public Product findById(Long id){
    return productRepository.findById(id)
        .orElseThrow(ProductNotFoundException::new);
  }

  public List<Product> findAllProducts(){
    return productRepository.findAll();
  }

  public List<Product> findAllBySupplier(Long supplierId){
    Optional<Supplier> supplier = supplierRepository.findById(supplierId);
    if(supplier.isPresent()){
      return supplier.get().getProducts();
    }
    throw new SupplierNotFoundException();
  }

  public Product createProduct(Product product){
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, ProductDto productDto){
    return productRepository.findById(id)
            .map(product -> {
              product.setPrice(productDto.getPrice());
              return product;
            }).orElseThrow(ProductNotFoundException::new);
  }

  public void deleteProduct(Long id){
    productRepository.deleteById(id);
  }
}