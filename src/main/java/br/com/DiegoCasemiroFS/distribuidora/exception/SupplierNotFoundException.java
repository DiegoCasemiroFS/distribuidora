package br.com.DiegoCasemiroFS.distribuidora.exception;

public class SupplierNotFoundException extends RuntimeException{

    public SupplierNotFoundException(){
        super("Cliente não encontrado");
    }

}
