package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.mensageria;

import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia.OutboxEventoJpaEntity;
import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia.SpringDataOutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxPublisherJob {

    private static final String TOPICO = "pedidos.criados";

    private final SpringDataOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxPublisherJob(SpringDataOutboxRepository outboxRepository,
                              KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelayString = "${pedidos.outbox.publicador.intervalo-ms:2000}")
    @Transactional
    public void publicarPendentes() {
        List<OutboxEventoJpaEntity> pendentes =
            outboxRepository.findTop50ByPublicadoFalseOrderByCriadoEmAsc();

        for (OutboxEventoJpaEntity evento : pendentes) {
            try {
                kafkaTemplate.send(TOPICO, evento.getEventoId(), evento.getPayload()).get();
                evento.marcarPublicado();
                outboxRepository.save(evento);
            } catch (Exception ex) {
                // Falha de publicação: o evento permanece pendente e será
                // tentado novamente no próximo ciclo. Logar e monitorar aqui.
                break;
            }
        }
    }
}
