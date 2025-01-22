package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String email;
    private String token;
    private boolean admin;
}
