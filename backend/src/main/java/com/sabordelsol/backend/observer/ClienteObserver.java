package com.sabordelsol.backend.observer;

import java.io.IOException;

public interface ClienteObserver {
    void notificar(String mensaje) throws IOException;
}
