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
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  private String cpf;

  @Email
  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phone;

  @OneToMany(mappedBy = "client")
  private List<Order> orders;
}
