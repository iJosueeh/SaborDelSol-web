package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.observer.ClienteSseObserver;
import com.sabordelsol.backend.observer.PromocionPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificationController {

    private final PromocionPublisher publisher;

    @GetMapping("/promociones")
    public SseEmitter subscribir() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        ClienteSseObserver observer = new ClienteSseObserver(emitter);

        publisher.registrar(observer);

        emitter.onCompletion(() -> publisher.eliminar(observer));
        emitter.onTimeout(() -> publisher.eliminar(observer));
        emitter.onError(e -> publisher.eliminar(observer));

        return emitter;
    }


}
