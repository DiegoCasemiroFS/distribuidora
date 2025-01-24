package br.com.DiegoCasemiroFS.distribuidora.entity.usuario;

import br.com.DiegoCasemiroFS.distribuidora.entity.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {

    private String contato;
    private Endereco endereco;
}
