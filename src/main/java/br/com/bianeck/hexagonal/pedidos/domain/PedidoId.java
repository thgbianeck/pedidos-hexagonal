package br.com.bianeck.hexagonal.pedidos.domain;

public record PedidoId(String valor) {
    public PedidoId {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("PedidoId não pode ser vazio");
        }
    }
}
