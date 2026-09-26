package br.com.bianeck.hexagonal.pedidos.application.usecase;

import br.com.bianeck.hexagonal.pedidos.application.command.CriarPedidoCommand;
import br.com.bianeck.hexagonal.pedidos.application.port.in.CriarPedidoPort;
import br.com.bianeck.hexagonal.pedidos.application.port.out.SalvarPedidoComEventoPort;
import br.com.bianeck.hexagonal.pedidos.domain.Pedido;
import br.com.bianeck.hexagonal.pedidos.domain.PedidoId;

import java.util.UUID;

public final class CriarPedidoUseCase implements CriarPedidoPort {

    private final SalvarPedidoComEventoPort salvarPedidoComEvento;

    public CriarPedidoUseCase(SalvarPedidoComEventoPort salvarPedidoComEvento) {
        this.salvarPedidoComEvento = salvarPedidoComEvento;
    }

    @Override
    public PedidoId executar(CriarPedidoCommand command) {
        PedidoId id = new PedidoId(UUID.randomUUID().toString());

        Pedido pedido = new Pedido(
                id,
                command.clienteId(),
                command.produtoId(),
                command.quantidade(),
                command.valorUnitario());

        String eventoId = UUID.randomUUID().toString();
        String payloadJson = """
                {"pedidoId":"%s","clienteId":"%s","produtoId":"%s","quantidade":%d,"valorTotal":%s}
                """.formatted(
                        pedido.id().valor(), pedido.clienteId(), pedido.produtoId(),
                        pedido.quantidade(), pedido.valorTotal())
                .strip();

        salvarPedidoComEvento.executar(pedido, eventoId, "PedidoCriado", payloadJson);

        return id;
    }
}