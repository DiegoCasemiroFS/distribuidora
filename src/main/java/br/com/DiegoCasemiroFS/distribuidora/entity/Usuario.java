package br.com.DiegoCasemiroFS.distribuidora.entity;

import br.com.DiegoCasemiroFS.distribuidora.entity.enums.TipoUsuario;
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
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String contato;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private boolean admin;
}
