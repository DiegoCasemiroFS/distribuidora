package br.com.DiegoCasemiroFS.demo.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String address;

  @Email
  @NotNull
  @Column(unique = true)
  private String email;

  @NotNull
  @Column(unique = true)
  private String phone;

  private List<Product> suppliedProducts;

}
