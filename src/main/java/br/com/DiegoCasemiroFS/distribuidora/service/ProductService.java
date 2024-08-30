package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProuctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProuctRepository prouctRepository;

    public Product findById(Long id){
        return prouctRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> findAll(){
        return prouctRepository.findAll();
    }

    public Product createProduct(Product product){
        return prouctRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequestDto productRequestDto){
        return prouctRepository.findById(id)
                .map(product -> {
                    product.setPrice(productRequestDto.getPrice());
                    return product;
                }).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(Long id){
        prouctRepository.deleteById(id);
    }
}
