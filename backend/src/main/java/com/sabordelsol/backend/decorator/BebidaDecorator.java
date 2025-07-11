package com.sabordelsol.backend.decorator;

public abstract class BebidaDecorator implements BebidaBase {

    protected BebidaBase bebidaDecorada;

    public BebidaDecorator(BebidaBase bebidaDecorada) {
        this.bebidaDecorada = bebidaDecorada;
    }

    public abstract String getDescripcion();
    public abstract double getPrecio();

}
