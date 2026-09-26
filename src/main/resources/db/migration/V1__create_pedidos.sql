-- V1__create_pedidos.sql
CREATE TABLE pedidos (
                         id CHAR(36) PRIMARY KEY,
                         cliente_id VARCHAR(64) NOT NULL,
                         produto_id VARCHAR(64) NOT NULL,
                         quantidade INT NOT NULL,
                         valor_unitario DECIMAL(12,2) NOT NULL,
                         status VARCHAR(20) NOT NULL
);
