package br.com.DiegoCasemiroFS.demo.entity;

import br.com.DiegoCasemiroFS.demo.entity.enums.Group;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private Long id;
  private String nome;
  private String marca;
  private Group grupo;
  private Double valor;
}