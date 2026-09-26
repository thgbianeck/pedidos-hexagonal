package br.com.bianeck.hexagonal.pedidos.infraestrutura;

import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia.OutboxEventoJpaEntity;
import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia.SpringDataOutboxRepository;
import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.mensageria.OutboxPublisherJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = "pedidos.criados")
class OutboxPublisherJobIT {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void propriedades(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private SpringDataOutboxRepository outboxRepository;

    @Autowired
    private OutboxPublisherJob publisherJob;

    @Test
    void devePublicarEventoPendenteEMarcarComoPublicado() {
        outboxRepository.save(new OutboxEventoJpaEntity(
            "evt-2", "PedidoCriado", "{\"pedidoId\":\"p-2\"}", false, Instant.now()));

        publisherJob.publicarPendentes();

        assertTrue(outboxRepository.findById("evt-2").get().isPublicado());
    }
}
