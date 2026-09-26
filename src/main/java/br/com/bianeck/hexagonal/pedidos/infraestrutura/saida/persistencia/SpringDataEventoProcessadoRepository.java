package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEventoProcessadoRepository
    extends JpaRepository<EventoProcessadoJpaEntity, String> {}
