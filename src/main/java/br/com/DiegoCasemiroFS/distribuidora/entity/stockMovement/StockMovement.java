package br.com.DiegoCasemiroFS.distribuidora.entity.stockMovement;

import br.com.DiegoCasemiroFS.distribuidora.entity.product.Product;
import br.com.DiegoCasemiroFS.distribuidora.entity.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_movement")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String productName;

    private Integer quantity;

    private Integer currentStock;

    private BigDecimal unitValue;

    private BigDecimal totalValue;

    private Integer movementType; // 1 - Entrada, 2 - Saída

    private LocalDateTime orderDate = LocalDateTime.now();
}
