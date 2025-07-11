package com.sabordelsol.backend.models.strategy;

import com.sabordelsol.backend.models.entity.Pedido;

public interface PromocionStrategy {
    void aplicar(Pedido pedido);
}
