package br.com.DiegoCasemiroFS.distribuidora.service;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.StockMovement;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.enums.UserType;
import br.com.DiegoCasemiroFS.distribuidora.exception.ProductNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.StockMovementNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.distribuidora.repository.ProductRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.StockMovementRepository;
import br.com.DiegoCasemiroFS.distribuidora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public StockMovement findById(Long id){
        return stockMovementRepository.findById(id)
                .orElseThrow(StockMovementNotFoundException::new);
    }

    public List<StockMovement> listAll(){
        return stockMovementRepository.findAll();
    }

    public StockMovementResponseDto saleToCostumer(StockMovementRequestDto stockMovementRequestDto){
        User user = userRepository.findById(stockMovementRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(stockMovementRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        if (product.getStock() >= stockMovementRequestDto.getQuantity()){
            product.setStock(product.getStock() - stockMovementRequestDto.getQuantity());
            productRepository.save(product);

            StockMovement stockMovement = new StockMovement();
            stockMovement.setUser(user);
            stockMovement.setUserName(user.getName());
            stockMovement.setProduct(product);
            stockMovement.setProductName(product.getName());
            stockMovement.setQuantity(stockMovementRequestDto.getQuantity());
            stockMovement.setCurrentStock(product.getStock());
            stockMovement.setUnitValue(product.getPrice());
            stockMovement.setTotalValue(product.getPrice().multiply(BigDecimal.valueOf(stockMovementRequestDto.getQuantity())));
            stockMovement.setMovementType(2);
            stockMovement.setOrderDate(LocalDateTime.now());

            stockMovementRepository.save(stockMovement);

            StockMovementResponseDto venda = new StockMovementResponseDto();
            venda.setUserName(user.getName());
            venda.setProductName(product.getName());
            venda.setQuantity(stockMovementRequestDto.getQuantity());
            venda.setTotalValue(product.getPrice().multiply(BigDecimal.valueOf(stockMovementRequestDto.getQuantity())));
            venda.setOrderDate(LocalDate.now());

            return venda;
        }
        throw new IllegalArgumentException("Quantidade insuficiente em estoque");
    }

    public StockMovementResponseDto supplierPurchase(StockMovementRequestDto stockMovementRequestDto){
        User user = userRepository.findById(stockMovementRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(stockMovementRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        if (user.getUserType() == UserType.FORNECEDOR){
            product.setStock(product.getStock() + stockMovementRequestDto.getQuantity());
            productRepository.save(product);

            StockMovement stockMovement = new StockMovement();
            stockMovement.setUser(user);
            stockMovement.setUserName(user.getName());
            stockMovement.setProduct(product);
            stockMovement.setProductName(product.getName());
            stockMovement.setQuantity(stockMovementRequestDto.getQuantity());
            stockMovement.setCurrentStock(product.getStock());
            stockMovement.setUnitValue(product.getPrice());
            stockMovement.setTotalValue(product.getPrice().multiply(BigDecimal.valueOf(stockMovementRequestDto.getQuantity())));
            stockMovement.setMovementType(1);
            stockMovement.setOrderDate(LocalDateTime.now());

            stockMovementRepository.save(stockMovement);

            StockMovementResponseDto compra = new StockMovementResponseDto();
            compra.setUserName(user.getName());
            compra.setProductName(product.getName());
            compra.setQuantity(stockMovementRequestDto.getQuantity());
            compra.setOrderDate(LocalDate.now());

            return compra;
        }
        throw new IllegalArgumentException("Usuário não é um fornecedor");
    }

    public void deleteStockMovement(Long id){
        stockMovementRepository.deleteById(id);
    }
}
