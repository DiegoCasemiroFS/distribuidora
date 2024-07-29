package br.com.DiegoCasemiroFS.distribuidora.entity;

import br.com.DiegoCasemiroFS.distribuidora.entity.enums.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
}
