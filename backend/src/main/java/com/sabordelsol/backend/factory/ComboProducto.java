package com.sabordelsol.backend.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComboProducto {
    private Long bebidaId;
    private List<Long> ingredientesExtraIds;
}
