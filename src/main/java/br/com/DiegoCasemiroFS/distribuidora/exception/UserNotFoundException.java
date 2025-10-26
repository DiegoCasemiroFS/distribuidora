package br.com.DiegoCasemiroFS.distribuidora.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("Usuário não encontrado!");
    }
}
