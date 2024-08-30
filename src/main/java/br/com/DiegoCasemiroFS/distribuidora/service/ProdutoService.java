package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Product findById(Long id){
        return produtoRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> findAll(){
        return produtoRepository.findAll();
    }

    public Product createProduct(Product product){
        return produtoRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequestDto productRequestDto){
        return produtoRepository.findById(id)
                .map(product -> {
                    product.setPrice(productRequestDto.getPrice());
                    return product;
                }).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(Long id){
        produtoRepository.deleteById(id);
    }
}
