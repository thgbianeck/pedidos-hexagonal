package br.com.bianeck.hexagonal.pedidos.application.usecase;

import br.com.bianeck.hexagonal.pedidos.application.command.ProcessarPedidoCriadoCommand;
import br.com.bianeck.hexagonal.pedidos.application.port.in.ProcessarPedidoCriadoPort;
import br.com.bianeck.hexagonal.pedidos.application.port.out.EventosProcessadosPort;

public final class ProcessarPedidoCriadoUseCase implements ProcessarPedidoCriadoPort {

    private final EventosProcessadosPort eventosProcessados;

    public ProcessarPedidoCriadoUseCase(EventosProcessadosPort eventosProcessados) {
        this.eventosProcessados = eventosProcessados;
    }

    @Override
    public void executar(ProcessarPedidoCriadoCommand command) {
        if (eventosProcessados.jaProcessado(command.eventoId())) {
            return; // idempotência: evento já processado anteriormente
        }

        // Aqui entraria a regra de negócio de reação ao evento
        // (ex.: notificar estoque, enviar e-mail, etc.)

        eventosProcessados.registrar(command.eventoId());
    }
}