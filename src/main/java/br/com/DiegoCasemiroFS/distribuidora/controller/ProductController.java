package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.CreateRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.StockRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.dto.PriceRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(productService.listAll());
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody CreateRequestDto createRequestDto) {
        return ResponseEntity.ok(productService.createProduct(createRequestDto));
    }

    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<Product> updatePrice(@PathVariable Long id, @RequestBody PriceRequestDto priceRequestDto) {
        return ResponseEntity.ok(productService.updatePrice(id, priceRequestDto));
    }

    @PutMapping("/addStock/{id}")
    public ResponseEntity<Product> addStock(@PathVariable Long id, @RequestBody StockRequestDto stockRequestDto) {
        return ResponseEntity.ok(productService.addStock(id, stockRequestDto));
    }

    @PutMapping("/removeStock/{id}")
    public ResponseEntity<Product> removeStock(@PathVariable Long id, @RequestBody StockRequestDto stockRequestDto) {
        return ResponseEntity.ok(productService.removeStock(id, stockRequestDto));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}