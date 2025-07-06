package com.sabordelsol.backend.factory;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ComboFactoryResolver {

    private final Map<String, ComboFactory> factories;

    public ComboFactoryResolver() {
        this.factories = Map.of(
                "DETOX", new ComboDetoxFactory(),
                "ENERGIA", new ComboEnergiaFactory()
        );
    }

    public ComboFactory resolver(String nombreCombo) {
        ComboFactory factory = factories.get(nombreCombo.toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Combo no encontrado: " + nombreCombo);
        }
        return factory;
    }

}
