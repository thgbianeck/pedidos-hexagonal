package br.com.bianeck.hexagonal.pedidos.application.command;

import java.math.BigDecimal;

public record CriarPedidoCommand(
    String clienteId,
    String produtoId,
    int quantidade,
    BigDecimal valorUnitario
) {}
