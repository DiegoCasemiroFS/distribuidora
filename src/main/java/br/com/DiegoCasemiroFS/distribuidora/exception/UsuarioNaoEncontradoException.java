package br.com.DiegoCasemiroFS.distribuidora.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado!");
    }
}
