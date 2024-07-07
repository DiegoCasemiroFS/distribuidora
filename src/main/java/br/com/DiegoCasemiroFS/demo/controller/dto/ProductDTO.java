package br.com.DiegoCasemiroFS.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO{

    private Long productCode;

    private String name;

    private Integer stockQuatity;
}
