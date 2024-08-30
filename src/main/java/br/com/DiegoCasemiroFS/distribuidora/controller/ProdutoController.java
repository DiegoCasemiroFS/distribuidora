package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/findProduct")
    public Product findProductById(@PathVariable Long id){
        return produtoService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Product> findAllProducts(){
        return produtoService.findAll();
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product){
        return produtoService.createProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        return produtoService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@PathVariable Long id){
        produtoService.deleteProduct(id);
    }
}
