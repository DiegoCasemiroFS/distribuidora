package br.com.DiegoCasemiroFS.demo.entity;

import jakarta.persistence.*;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String nome;

  @NotNull
  private String endereco;

  @Email
  @NotNull
  @Column(unique = true)
  private String email;

  @NotNull
  @Column(unique = true)
  private String telefone;

}
