package br.com.bianeck.hexagonal.pedidos.application.command;

public record ProcessarPedidoCriadoCommand(
    String eventoId,
    String pedidoId,
    String clienteId
) {}
