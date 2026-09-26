package br.com.bianeck.hexagonal.pedidos.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCalcularValorTotalCorretamente() {
        var pedido = new Pedido(
            new PedidoId("p-1"), "cliente-1", "produto-1", 3, new BigDecimal("10.00"));

        assertEquals(new BigDecimal("30.00"), pedido.valorTotal());
    }

    @Test
    void deveRejeitarQuantidadeInvalida() {
        assertThrows(QuantidadeInvalidaException.class, () ->
            new Pedido(new PedidoId("p-1"), "cliente-1", "produto-1", 0, BigDecimal.TEN));
    }
}
