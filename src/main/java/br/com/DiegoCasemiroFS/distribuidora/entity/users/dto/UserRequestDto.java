package br.com.DiegoCasemiroFS.distribuidora.entity.users.dto;

import br.com.DiegoCasemiroFS.distribuidora.entity.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String phone;
    private Address address;
}
