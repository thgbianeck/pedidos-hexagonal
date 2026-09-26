package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "outbox_eventos")
public class OutboxEventoJpaEntity {

    @Id
    @Column(name = "evento_id", length = 36)
    private String eventoId;

    @Column(name = "tipo_evento", nullable = false)
    private String tipoEvento;

    @Column(name = "payload", columnDefinition = "JSON", nullable = false)
    private String payload;

    @Column(name = "publicado", nullable = false)
    private boolean publicado;

    @Column(name = "criado_em", nullable = false)
    private Instant criadoEm;

    protected OutboxEventoJpaEntity() {}

    public OutboxEventoJpaEntity(String eventoId, String tipoEvento,
                                 String payload, boolean publicado, Instant criadoEm) {
        this.eventoId = eventoId;
        this.tipoEvento = tipoEvento;
        this.payload = payload;
        this.publicado = publicado;
        this.criadoEm = criadoEm;
    }

    public String getEventoId() { return eventoId; }
    public String getTipoEvento() { return tipoEvento; }
    public String getPayload() { return payload; }
    public boolean isPublicado() { return publicado; }
    public void marcarPublicado() { this.publicado = true; }
    public Instant getCriadoEm() { return criadoEm; }
}
