package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Produto;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProdutoRequestDto;
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
    public Produto findProductById(@PathVariable Long id){
        return produtoService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Produto> findAllProducts(){
        return produtoService.findAll();
    }

    @PostMapping("/createProduct")
    public Produto createProduct(@RequestBody Produto produto){
        return produtoService.createProduct(produto);
    }

    @PutMapping("/updateProduct")
    public Produto updateProduct(@PathVariable Long id, @RequestBody ProdutoRequestDto produtoRequestDto){
        return produtoService.updateProduct(id, produtoRequestDto);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@PathVariable Long id){
        produtoService.deleteProduct(id);
    }
}
