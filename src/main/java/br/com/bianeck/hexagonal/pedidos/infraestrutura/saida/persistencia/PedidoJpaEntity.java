package br.com.bianeck.hexagonal.pedidos.infraestrutura.saida.persistencia;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "cliente_id", nullable = false)
    private String clienteId;

    @Column(name = "produto_id", nullable = false)
    private String produtoId;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "valor_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "status", nullable = false)
    private String status;

    protected PedidoJpaEntity() {}

    public PedidoJpaEntity(String id, String clienteId, String produtoId,
                           int quantidade, BigDecimal valorUnitario, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.status = status;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public String getProdutoId() { return produtoId; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public String getStatus() { return status; }
}
