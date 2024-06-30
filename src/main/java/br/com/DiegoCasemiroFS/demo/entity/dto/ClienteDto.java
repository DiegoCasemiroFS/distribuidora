package br.com.DiegoCasemiroFS.demo.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDto {
  @NotNull
  private String endereco;
  @Email
  @NotNull
  private String email;
  @NotNull
  private String telefone;

}
