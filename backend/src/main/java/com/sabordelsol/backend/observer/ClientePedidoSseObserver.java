package com.sabordelsol.backend.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@AllArgsConstructor
public class ClientePedidoSseObserver implements PedidoObserver {

    private final Long usuarioId;
    private final SseEmitter emitter;

    @Override
    public void notificar(String mensaje) throws IOException {
        emitter.send(SseEmitter.event()
                .name("pedido-estado")
                .data(mensaje));
    }
}
