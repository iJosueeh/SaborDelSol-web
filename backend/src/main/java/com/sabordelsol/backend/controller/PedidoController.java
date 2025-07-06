package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.command.concrete.GenerarComprobanteCommand;
import com.sabordelsol.backend.models.dto.*;
import com.sabordelsol.backend.models.entity.Pedido;
import com.sabordelsol.backend.repository.PedidoRepository;
import com.sabordelsol.backend.services.PdfService;
import com.sabordelsol.backend.services.PedidoService;
import com.sabordelsol.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PdfService pdfService;
    private final PedidoRepository pedidoRepo;
    private final JwtUtil jwtUtil;

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

    @GetMapping("/mis-pedidos/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> listarPedidosUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(usuarioId));
    }

    @GetMapping("/dashboard/admin")
    public ResponseEntity<DashboardAdminDTO> dashboardAdmin() {
        return ResponseEntity.ok(pedidoService.getDashboardAdmin());
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/mis-pedidos")
    public ResponseEntity<List<PedidoClienteDTO>> misPedidos(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String correo = jwtUtil.extraerCorreo(token);

        return ResponseEntity.ok(pedidoService.listarPedidosDeCliente(correo));
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping("/reordenar/{pedidoId}")
    public ResponseEntity<Pedido> reordenar(@PathVariable Long pedidoId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String correo = jwtUtil.extraerCorreo(token);

        Pedido nuevoPedido = pedidoService.reordenarPedido(pedidoId, correo);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/comprobante/{id}")
    public ResponseEntity<byte[]> generarComprobante(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String correo = jwtUtil.extraerCorreo(token);

        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getUsuario().getCorreo().equals(correo)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Aplicación del patrón Command
        GenerarComprobanteCommand comando = new GenerarComprobanteCommand(pdfService, pedido);
        comando.ejecutar();

        ByteArrayInputStream pdfStream = comando.getResultado();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=comprobante-pedido.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }

    @PostMapping("/agregar-combo")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Pedido> agregarCombo(@RequestBody ComboRequestDTO dto, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String correo = jwtUtil.extraerCorreo(token);

        Pedido pedido = pedidoService.crearPedidoDesdeCombo(dto, correo);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    ) {
        Pedido actualizado = pedidoService.actualizarEstado(id, estado.toUpperCase());
        return ResponseEntity.ok(actualizado);
    }

}
