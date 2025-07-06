package com.sabordelsol.backend.models.strategy;

import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DescuentoFijoStrategy implements PromocionStrategy {

    private final double descuento;

    @Override
    public void aplicar(Pedido pedido) {
        double total = pedido.getTotal();
        pedido.setTotal(Math.max(0, total - descuento));
        System.out.println("Se aplicó S/ " + descuento + " de descuento");
    }
}
