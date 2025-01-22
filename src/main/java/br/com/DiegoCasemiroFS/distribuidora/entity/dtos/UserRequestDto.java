package br.com.DiegoCasemiroFS.distribuidora.entity.dtos;

import br.com.DiegoCasemiroFS.distribuidora.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private Address address;
    private String phone;
}
