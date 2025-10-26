package br.com.DiegoCasemiroFS.distribuidora.exception;

public class LoginNotSuccessfulException extends RuntimeException{

    public LoginNotSuccessfulException(){
        super("Email ou senha invalido, tente novamente!");
    }
}
