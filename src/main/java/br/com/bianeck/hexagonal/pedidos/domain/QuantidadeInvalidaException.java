package br.com.bianeck.hexagonal.pedidos.domain;

public final class QuantidadeInvalidaException extends RuntimeException {
    public QuantidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
