package br.com.DiegoCasemiroFS.distribuidora.exception;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(){
        super("Cliente não encontrado");
    }

}
