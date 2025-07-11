package com.sabordelsol.backend.decorator;

import com.sabordelsol.backend.models.entity.IngredienteExtra;

public class IngredienteExtraDecorator extends BebidaDecorator {

    private final IngredienteExtra ingrediente;

    public IngredienteExtraDecorator(BebidaBase bebidaDecorada, IngredienteExtra ingrediente) {
        super(bebidaDecorada);
        this.ingrediente = ingrediente;
    }

    @Override
    public String getDescripcion() {
        return bebidaDecorada.getDescripcion() + " + " + ingrediente.getNombre();
    }

    @Override
    public double getPrecio() {
        return bebidaDecorada.getPrecio() + ingrediente.getPrecioExtra();
    }
}
