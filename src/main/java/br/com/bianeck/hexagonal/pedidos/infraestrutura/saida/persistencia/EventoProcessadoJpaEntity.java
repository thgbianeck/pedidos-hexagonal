package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import jakarta.persistence.*;

@Entity
@Table(name = "eventos_processados")
public class EventoProcessadoJpaEntity {

    @Id
    @Column(name = "evento_id", length = 36)
    private String eventoId;

    protected EventoProcessadoJpaEntity() {}

    public EventoProcessadoJpaEntity(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getEventoId() { return eventoId; }
}
