package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.models.dto.PedidoRequestDTO;
import com.sabordelsol.backend.models.entity.Pedido;
import com.sabordelsol.backend.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequestDTO dto) {
        Pedido nuevoPedido = pedidoService.crearPedido(dto);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{pedidoId}/quitar")
    public ResponseEntity<Pedido> quitarProducto(@PathVariable Long pedidoId,
                                                 @RequestParam Long bebidaId,
                                                 @RequestParam int cantidad) {
        Pedido actualizado = pedidoService.quitarProductoDelPedido(pedidoId, bebidaId, cantidad);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Pedido> confirmarPedido(@PathVariable Long id) {
        Pedido confirmado = pedidoService.confirmarPedido(id);
        return ResponseEntity.ok(confirmado);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        Pedido cancelado = pedidoService.cancelarPedido(id);
        return ResponseEntity.ok(cancelado);
    }

}
