package br.com.DiegoCasemiroFS.distribuidora.controller;

import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.StockMovement;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementRequestDto;
import br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement.dto.StockMovementResponseDto;
import br.com.DiegoCasemiroFS.distribuidora.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockMovement")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<StockMovement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementService.findById(id));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<StockMovement>> listAll() {
        return ResponseEntity.ok(stockMovementService.listAll());
    }

    @PostMapping("/saleToCostumer")
    public ResponseEntity<StockMovementResponseDto> saleToCostumer(@RequestBody StockMovementRequestDto stockMovementRequestDto) {
        return ResponseEntity.ok(stockMovementService.saleToCostumer(stockMovementRequestDto));
    }

    @PostMapping("/supplierPurchase")
    public ResponseEntity<StockMovementResponseDto> supplierPurchase(@RequestBody StockMovementRequestDto stockMovementRequestDto) {
        return ResponseEntity.ok(stockMovementService.supplierPurchase(stockMovementRequestDto));
    }

    @DeleteMapping("/deleteStockMovement/{id}")
    public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
        stockMovementService.deleteStockMovement(id);
        return ResponseEntity.noContent().build();
    }
}