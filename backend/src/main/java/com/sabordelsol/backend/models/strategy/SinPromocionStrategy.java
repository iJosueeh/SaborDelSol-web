package com.sabordelsol.backend.models.strategy;

import com.sabordelsol.backend.models.entity.Pedido;

public class SinPromocionStrategy implements PromocionStrategy {
    @Override
    public void aplicar(Pedido pedido) {
        System.out.println("No se aplicó promoción");
    }
}
