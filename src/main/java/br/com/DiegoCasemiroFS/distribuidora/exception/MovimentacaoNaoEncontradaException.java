package br.com.DiegoCasemiroFS.distribuidora.exception;

public class MovimentacaoNaoEncontradaException extends RuntimeException{

    public MovimentacaoNaoEncontradaException(){
        super("Pedido não encontrado!");
    }
}
