package br.com.bianeck.hexagonal.pedidos.infraestrutura.entrada.rest;

import br.com.bianeck.hexagonal.pedidos.application.command.CriarPedidoCommand;
import br.com.bianeck.hexagonal.pedidos.application.port.in.CriarPedidoPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoPort criarPedidoPort;

    public PedidoController(CriarPedidoPort criarPedidoPort) {
        this.criarPedidoPort = criarPedidoPort;
    }

    @PostMapping
    public ResponseEntity<CriarPedidoResponseDTO> criar(
        @Valid @RequestBody CriarPedidoRequestDTO request) {

        var command = new CriarPedidoCommand(
            request.clienteId(),
            request.produtoId(),
            request.quantidade(),
            request.valorUnitario());

        var pedidoId = criarPedidoPort.executar(command);

        return ResponseEntity
            .accepted()
            .body(new CriarPedidoResponseDTO(pedidoId.valor()));
    }
}
