package br.com.bianeck.hexagonal.pedidos.domain;

import java.math.BigDecimal;

public final class Pedido {

    private final PedidoId id;
    private final String clienteId;
    private final String produtoId;
    private final int quantidade;
    private final BigDecimal valorUnitario;
    private PedidoStatus status;

    public Pedido(PedidoId id, String clienteId, String produtoId,
                  int quantidade, BigDecimal valorUnitario) {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser positiva");
        }
        if (valorUnitario == null || valorUnitario.signum() <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser positivo");
        }

        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.status = PedidoStatus.CRIADO;
    }

    public BigDecimal valorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public PedidoId id() { return id; }
    public String clienteId() { return clienteId; }
    public String produtoId() { return produtoId; }
    public int quantidade() { return quantidade; }
    public BigDecimal valorUnitario() { return valorUnitario; }
    public PedidoStatus status() { return status; }
}
