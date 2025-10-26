package br.com.DiegoCasemiroFS.distribuidora.exception;

public class StockMovementNotFoundException extends RuntimeException{

    public StockMovementNotFoundException(){
        super("Pedido não encontrado!");
    }
}
