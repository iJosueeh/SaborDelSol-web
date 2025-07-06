package com.sabordelsol.backend.observer;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PedidoNotifier {

    private final List<ClientePedidoSseObserver> observadores = new CopyOnWriteArrayList<>();

    public void registrar(ClientePedidoSseObserver observer) {
        observadores.add(observer);
    }

    public void eliminar(ClientePedidoSseObserver observer) {
        observadores.remove(observer);
    }

    public void notificarA(Long usuarioId, String mensaje) {
        for (ClientePedidoSseObserver observer : observadores) {
            if (observer.getUsuarioId().equals(usuarioId)) {
                try {
                    observer.notificar(mensaje);
                } catch (IOException e) {
                    eliminar(observer);
                }
            }
        }
    }

}
