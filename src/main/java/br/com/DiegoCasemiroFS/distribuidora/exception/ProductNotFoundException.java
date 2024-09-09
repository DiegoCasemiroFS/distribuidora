package br.com.DiegoCasemiroFS.distribuidora.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(){
        super("Produto não encontrado, informe um Id válido!");
    }
}
