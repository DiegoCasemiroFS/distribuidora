package br.com.DiegoCasemiroFS.distribuidora.entity.usuario;

import br.com.DiegoCasemiroFS.distribuidora.entity.endereco.Endereco;
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
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    @Embedded
    private Endereco address;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean admin;
}
