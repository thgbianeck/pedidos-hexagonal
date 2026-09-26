package br.com.bianeck.hexagonal.pedidos.infraestrutura.entrada.kafka;

public record PedidoCriadoEventPayload(
    String pedidoId,
    String clienteId,
    String produtoId,
    int quantidade,
    String valorTotal
) {}
