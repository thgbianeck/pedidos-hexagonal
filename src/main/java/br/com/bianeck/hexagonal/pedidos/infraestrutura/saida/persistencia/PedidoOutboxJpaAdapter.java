package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import br.com.bianeck.hexagonal.pedidos.application.port.out.SalvarPedidoComEventoPort;
import br.com.bianeck.hexagonal.pedidos.domain.Pedido;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class PedidoOutboxJpaAdapter implements SalvarPedidoComEventoPort {

    private final SpringDataPedidoRepository pedidoRepository;
    private final SpringDataOutboxRepository outboxRepository;
    private final PedidoMapper mapper;

    public PedidoOutboxJpaAdapter(SpringDataPedidoRepository pedidoRepository,
                                  SpringDataOutboxRepository outboxRepository,
                                  PedidoMapper mapper) {
        this.pedidoRepository = pedidoRepository;
        this.outboxRepository = outboxRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void executar(Pedido pedido, String eventoId, String tipoEvento, String payloadJson) {
        pedidoRepository.save(mapper.paraJpa(pedido));

        outboxRepository.save(new OutboxEventoJpaEntity(
            eventoId, tipoEvento, payloadJson, false, Instant.now()));
    }
}
