package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import br.com.bianeck.hexagonal.pedidos.application.port.out.EventosProcessadosPort;
import org.springframework.stereotype.Component;

@Component
public class EventosProcessadosJpaAdapter implements EventosProcessadosPort {

    private final SpringDataEventoProcessadoRepository repository;

    public EventosProcessadosJpaAdapter(SpringDataEventoProcessadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean jaProcessado(String eventoId) {
        return repository.existsById(eventoId);
    }

    @Override
    public void registrar(String eventoId) {
        repository.save(new EventoProcessadoJpaEntity(eventoId));
    }
}
