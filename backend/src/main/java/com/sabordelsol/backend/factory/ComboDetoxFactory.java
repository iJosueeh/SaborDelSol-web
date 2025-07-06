package com.sabordelsol.backend.factory;

import java.util.List;

public class ComboDetoxFactory implements ComboFactory {

    @Override
    public ComboProducto crearCombo() {
        return new ComboProducto() {{
            setBebidaId(1L);
            setIngredientesExtraIds(List.of(2L, 5L, 8L));
        }};
    }
}
