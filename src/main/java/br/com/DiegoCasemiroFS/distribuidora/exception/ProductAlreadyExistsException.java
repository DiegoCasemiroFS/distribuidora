package br.com.DiegoCasemiroFS.distribuidora.exception;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException(){
        super("Esse produto já existe no banco de dados!");
    }
}
