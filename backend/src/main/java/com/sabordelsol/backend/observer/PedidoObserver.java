package com.sabordelsol.backend.observer;

import java.io.IOException;

public interface PedidoObserver {
    void notificar(String mensaje) throws IOException;
}
