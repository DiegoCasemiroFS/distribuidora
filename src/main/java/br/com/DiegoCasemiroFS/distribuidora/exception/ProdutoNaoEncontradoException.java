package br.com.DiegoCasemiroFS.distribuidora.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(){
        super("Produto não encontrado!");
    }
}
