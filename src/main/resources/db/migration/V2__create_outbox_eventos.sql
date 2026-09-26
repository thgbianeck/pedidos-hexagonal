-- V2__create_outbox_eventos.sql
CREATE TABLE outbox_eventos (
                                evento_id CHAR(36) PRIMARY KEY,
                                tipo_evento VARCHAR(64) NOT NULL,
                                payload JSON NOT NULL,
                                publicado BOOLEAN NOT NULL DEFAULT FALSE,
                                criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                INDEX idx_publicado_criado (publicado, criado_em)
);
