package br.com.DiegoCasemiroFS.distribuidora.exception;

public class InsufficientValueException extends RuntimeException {
    public InsufficientValueException(){
        super("Só é possivel executar a operação com valores maiores que 0!");
    }
}
