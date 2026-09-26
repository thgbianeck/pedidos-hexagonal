-- V3__create_eventos_processados.sql
CREATE TABLE eventos_processados (
                                     evento_id CHAR(36) PRIMARY KEY,
                                     processado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
