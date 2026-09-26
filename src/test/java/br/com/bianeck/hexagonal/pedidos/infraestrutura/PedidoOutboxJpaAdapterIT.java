package br.com.bianeck.hexagonal.pedidos.infraestrutura;

import br.com.bianeck.hexagonal.pedidos.domain.Pedido;
import br.com.bianeck.hexagonal.pedidos.domain.PedidoId;
import br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class PedidoOutboxJpaAdapterIT {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void propriedades(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private PedidoOutboxJpaAdapter adapter;

    @Autowired
    private SpringDataPedidoRepository pedidoRepository;

    @Autowired
    private SpringDataOutboxRepository outboxRepository;

    @Test
    void devePersistirPedidoEEventoNaMesmaOperacao() {
        var pedido = new Pedido(
            new PedidoId("p-teste"), "cliente-x", "produto-y", 1, new BigDecimal("50.00"));

        adapter.executar(pedido, "evt-1", "PedidoCriado", "{\"pedidoId\":\"p-teste\"}");

        assertTrue(pedidoRepository.existsById("p-teste"));
        assertTrue(outboxRepository.existsById("evt-1"));
        assertFalse(outboxRepository.findById("evt-1").get().isPublicado());
    }
}
