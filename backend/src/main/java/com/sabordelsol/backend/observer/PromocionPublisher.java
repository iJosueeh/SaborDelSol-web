package com.sabordelsol.backend.observer;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PromocionPublisher {

    private final List<ClienteSseObserver> observadores = new CopyOnWriteArrayList<>();

    public void registrar(ClienteSseObserver observer) {
        observadores.add(observer);
    }

    public void eliminar(ClienteSseObserver observer) {
        observadores.remove(observer);
    }

    public void notificarTodos(String mensaje) {
        for (ClienteSseObserver observer : observadores) {
            try {
                observer.notificar(mensaje);
            } catch (IOException e) {
                eliminar(observer);
            }
        }
    }
}

