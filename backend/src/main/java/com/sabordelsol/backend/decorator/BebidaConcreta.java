package com.sabordelsol.backend.decorator;

import com.sabordelsol.backend.models.entity.Bebida;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BebidaConcreta implements BebidaBase {

    private final Bebida bebida;

    @Override
    public String getDescripcion() {
        return bebida.getNombre();
    }

    @Override
    public double getPrecio() {
        return bebida.getPrecioBase();
    }
}
