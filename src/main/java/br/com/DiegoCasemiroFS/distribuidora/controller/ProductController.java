package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.dtos.ProductRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findProduct")
    public Product findProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        return productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
