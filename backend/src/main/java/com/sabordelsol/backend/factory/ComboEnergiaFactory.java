package com.sabordelsol.backend.factory;

import java.util.List;

public class ComboEnergiaFactory implements ComboFactory {

    @Override
    public ComboProducto crearCombo() {
        return new ComboProducto() {{
            setBebidaId(3L); // Jugo de Plátano
            setIngredientesExtraIds(List.of(4L, 6L)); // miel, plátano
        }};
    }
}
