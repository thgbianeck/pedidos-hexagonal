package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOutboxRepository extends JpaRepository<OutboxEventoJpaEntity, String> {
    List<OutboxEventoJpaEntity> findTop50ByPublicadoFalseOrderByCriadoEmAsc();
}
