package com.sabordelsol.backend.factory;

import com.sabordelsol.backend.models.entity.Combo;
import com.sabordelsol.backend.models.entity.ComboItem;

import java.util.List;

public class ComboFitnessFactory implements ComboFactory {
    @Override
    public Combo crearCombo() {
        return Combo.builder()
                .nombre("Combo Fitness")
                .descripcion("Jugo verde + barrita de granola + yogurt")
                .precio(15.00)
                .items(List.of(
                        ComboItem.builder().tipoItem("bebida").itemId(1L).build(),
                        ComboItem.builder().tipoItem("snack").itemId(2L).build(),
                        ComboItem.builder().tipoItem("postre").itemId(3L).build()
                ))
                .build();
    }
}
