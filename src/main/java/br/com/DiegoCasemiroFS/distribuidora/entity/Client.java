package br.com.DiegoCasemiroFS.distribuidora.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  @Email
  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phone;

  @OneToMany(mappedBy = "client")
  private List<Order> orders;
}
