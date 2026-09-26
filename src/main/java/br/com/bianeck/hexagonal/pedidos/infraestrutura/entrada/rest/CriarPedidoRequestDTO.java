package br.com.bianeck.hexagonal.pedidos.infraestrutura.entrada.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CriarPedidoRequestDTO(
    @NotBlank String clienteId,
    @NotBlank String produtoId,
    @Positive int quantidade,
    @Positive BigDecimal valorUnitario
) {}
