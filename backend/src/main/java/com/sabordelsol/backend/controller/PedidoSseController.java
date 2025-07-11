package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.observer.ClientePedidoSseObserver;
import com.sabordelsol.backend.observer.PedidoNotifier;
import com.sabordelsol.backend.repository.UsuarioRepository;
import com.sabordelsol.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoSseController {

    private final PedidoNotifier pedidoNotifier;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepo;

    @GetMapping("/pedidos")
    public SseEmitter conectar(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String correo = jwtUtil.extraerCorreo(token);

        Long usuarioId = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                .getId();

        SseEmitter emitter = new SseEmitter(0L);
        ClientePedidoSseObserver observer = new ClientePedidoSseObserver(usuarioId, emitter);

        pedidoNotifier.registrar(observer);

        emitter.onCompletion(() -> pedidoNotifier.eliminar(observer));
        emitter.onTimeout(() -> pedidoNotifier.eliminar(observer));
        emitter.onError(e -> pedidoNotifier.eliminar(observer));

        return emitter;
    }

}
