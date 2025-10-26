package br.com.DiegoCasemiroFS.distribuidora.entity.users;

import br.com.DiegoCasemiroFS.distribuidora.entity.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean admin;
}
