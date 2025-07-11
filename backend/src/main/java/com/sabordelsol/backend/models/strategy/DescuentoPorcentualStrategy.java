package com.sabordelsol.backend.models.strategy;

import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DescuentoPorcentualStrategy implements PromocionStrategy {

    private final double porcentaje;

    @Override
    public void aplicar(Pedido pedido) {
        double total = pedido.getTotal();
        double descuento = total * (porcentaje / 100.0);
        pedido.setTotal(total - descuento);
        System.out.println("Se aplicó " + porcentaje + "% de descuento");
    }
}
