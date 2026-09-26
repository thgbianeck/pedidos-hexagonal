package br.com.bianeck.hexagonal.pedidos.application.port.in;

import br.com.bianeck.hexagonal.pedidos.application.command.CriarPedidoCommand;
import br.com.bianeck.hexagonal.pedidos.domain.PedidoId;

public interface CriarPedidoPort {
    PedidoId executar(CriarPedidoCommand command);
}