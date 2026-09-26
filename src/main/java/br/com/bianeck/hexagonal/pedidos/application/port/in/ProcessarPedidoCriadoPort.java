package br.com.bianeck.hexagonal.pedidos.application.port.in;

import br.com.bianeck.hexagonal.pedidos.application.command.ProcessarPedidoCriadoCommand;

public interface ProcessarPedidoCriadoPort {
    void executar(ProcessarPedidoCriadoCommand command);
}