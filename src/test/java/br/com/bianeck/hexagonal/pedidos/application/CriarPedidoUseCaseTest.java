package br.com.bianeck.hexagonal.pedidos.application;

import br.com.bianeck.hexagonal.pedidos.application.command.CriarPedidoCommand;
import br.com.bianeck.hexagonal.pedidos.application.port.out.SalvarPedidoComEventoPort;
import br.com.bianeck.hexagonal.pedidos.application.usecase.CriarPedidoUseCase;
import br.com.bianeck.hexagonal.pedidos.domain.Pedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriarPedidoUseCaseTest {

    @Test
    void deveSalvarPedidoComEventoAssociado() {
        List<Pedido> salvos = new ArrayList<>();

        SalvarPedidoComEventoPort fake = (pedido, eventoId, tipo, payload) -> salvos.add(pedido);

        var useCase = new CriarPedidoUseCase(fake);
        var id = useCase.executar(new CriarPedidoCommand(
            "cliente-1", "produto-1", 2, new BigDecimal("15.00")));

        assertNotNull(id);
        assertEquals(1, salvos.size());
        assertEquals(new BigDecimal("30.00"), salvos.get(0).valorTotal());
    }
}
