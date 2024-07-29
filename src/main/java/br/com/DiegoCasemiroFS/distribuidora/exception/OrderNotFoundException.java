package br.com.DiegoCasemiroFS.distribuidora.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(){
        super("Pedido não encontrado");
    }
}
