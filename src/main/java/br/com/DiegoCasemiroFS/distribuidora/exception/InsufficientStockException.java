package br.com.DiegoCasemiroFS.distribuidora.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(){
        super ("O estoque não pode ser menor que 0!");
    }
}
