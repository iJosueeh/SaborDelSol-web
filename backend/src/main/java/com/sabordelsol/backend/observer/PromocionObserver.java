package com.sabordelsol.backend.observer;

import java.io.IOException;

public interface PromocionObserver {
    void notificar(String mensaje) throws IOException;
}
