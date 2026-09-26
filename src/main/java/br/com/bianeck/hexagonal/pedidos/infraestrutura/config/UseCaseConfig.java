package br.com.bianeck.hexagonal.pedidos.infraestrutura.config;

import br.com.bianeck.hexagonal.pedidos.application.port.in.CriarPedidoPort;
import br.com.bianeck.hexagonal.pedidos.application.port.in.ProcessarPedidoCriadoPort;
import br.com.bianeck.hexagonal.pedidos.application.port.out.EventosProcessadosPort;
import br.com.bianeck.hexagonal.pedidos.application.port.out.SalvarPedidoComEventoPort;
import br.com.bianeck.hexagonal.pedidos.application.usecase.CriarPedidoUseCase;
import br.com.bianeck.hexagonal.pedidos.application.usecase.ProcessarPedidoCriadoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CriarPedidoPort criarPedidoPort(SalvarPedidoComEventoPort salvarPedidoComEventoPort) {
        return new CriarPedidoUseCase(salvarPedidoComEventoPort);
    }

    @Bean
    public ProcessarPedidoCriadoPort processarPedidoCriadoPort(
        EventosProcessadosPort eventosProcessadosPort) {
        return new ProcessarPedidoCriadoUseCase(eventosProcessadosPort);
    }
}
