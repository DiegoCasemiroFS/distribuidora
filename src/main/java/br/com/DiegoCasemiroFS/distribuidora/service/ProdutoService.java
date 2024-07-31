package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProdutoRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto createProduct(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto updateProduct(Long id, ProdutoRequestDto produtoRequestDto){
        return produtoRepository.findById(id)
                .map(product -> {
                    product.setPrice(produtoRequestDto.getPrice());
                    return product;
                }).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(Long id){
        produtoRepository.deleteById(id);
    }
}
