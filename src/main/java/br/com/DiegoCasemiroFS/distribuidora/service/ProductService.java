package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.CreateRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.PriceRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.product.StockRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductAlreadyExistsException;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public Product createProduct(CreateRequestDto createRequestDto){

        if(!productRepository.existsByNameIgnoreCase(createRequestDto.getName())){
            Product product = new Product();

            product.setName(createRequestDto.getName());
            product.setProductType(createRequestDto.getProductType());
            product.setPrice(createRequestDto.getPrice());
            product.setStock(createRequestDto.getQuantity());

            return productRepository.save(product);
        }
        throw new ProductAlreadyExistsException();
    }

    public Product updatePrice(Long id, PriceRequestDto priceRequestDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setPrice(priceRequestDto.getPrice());
                    return productRepository.save(product);
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    public Product updateStock(Long id, StockRequestDto stockRequestDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setStock(product.getStock() + stockRequestDto.getQuantity());
                    return productRepository.save(product);
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
