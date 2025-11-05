package br.com.DiegoCasemiroFS.distribuidora.entity.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String email;
    private String token;
    private boolean admin;
}
