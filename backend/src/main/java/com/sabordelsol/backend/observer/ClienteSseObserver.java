package com.sabordelsol.backend.observer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@RequiredArgsConstructor
public class ClienteSseObserver implements ClienteObserver {

    private final SseEmitter emitter;

    @Override
    public void notificar(String mensaje) throws IOException {
        emitter.send(SseEmitter.event()
                .name("promocion-nueva")
                .data(mensaje));
    }

}
