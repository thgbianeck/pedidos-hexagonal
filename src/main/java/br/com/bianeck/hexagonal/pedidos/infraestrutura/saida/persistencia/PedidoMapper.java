package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import br.com.bianeck.hexagonal.pedidos.domain.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoJpaEntity paraJpa(Pedido pedido) {
        return new PedidoJpaEntity(
            pedido.id().valor(),
            pedido.clienteId(),
            pedido.produtoId(),
            pedido.quantidade(),
            pedido.valorUnitario(),
            pedido.status().name());
    }
}
