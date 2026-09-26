package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPedidoRepository extends JpaRepository<PedidoJpaEntity, String> {}
