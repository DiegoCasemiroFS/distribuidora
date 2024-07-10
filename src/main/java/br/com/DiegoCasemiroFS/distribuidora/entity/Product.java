package br.com.DiegoCasemiroFS.distribuidora.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

//  @Enumerated(EnumType.STRING)
//  private Group group;

  @Column(name = "preco_unitario")
  private BigDecimal price;
}