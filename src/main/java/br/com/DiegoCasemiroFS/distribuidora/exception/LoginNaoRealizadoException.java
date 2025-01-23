package br.com.DiegoCasemiroFS.distribuidora.exception;

public class LoginNaoRealizadoException extends RuntimeException{

    public LoginNaoRealizadoException(){
        super("Email ou senha invalido, tente novamente!");
    }
}
