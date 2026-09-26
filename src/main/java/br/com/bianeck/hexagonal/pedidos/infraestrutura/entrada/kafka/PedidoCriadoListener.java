package br.com.bianeck.hexagonal.pedidos.infraestrutura.entrada.kafka;

import br.com.bianeck.hexagonal.pedidos.application.command.ProcessarPedidoCriadoCommand;
import br.com.bianeck.hexagonal.pedidos.application.port.in.ProcessarPedidoCriadoPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoCriadoListener {

    private final ProcessarPedidoCriadoPort processarPedidoCriadoPort;
    private final ObjectMapper objectMapper;

    public PedidoCriadoListener(ProcessarPedidoCriadoPort processarPedidoCriadoPort,
                                ObjectMapper objectMapper) {
        this.processarPedidoCriadoPort = processarPedidoCriadoPort;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "pedidos.criados", groupId = "pedidos-service")
    public void onMessage(@Payload String payloadJson,
                          @Header(KafkaHeaders.RECEIVED_KEY) String eventoId) throws Exception {

        PedidoCriadoEventPayload payload =
            objectMapper.readValue(payloadJson, PedidoCriadoEventPayload.class);

        var command = new ProcessarPedidoCriadoCommand(
            eventoId, payload.pedidoId(), payload.clienteId());

        processarPedidoCriadoPort.executar(command);
    }
}
