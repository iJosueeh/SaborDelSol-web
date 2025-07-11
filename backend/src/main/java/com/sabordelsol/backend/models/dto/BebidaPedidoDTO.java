package com.sabordelsol.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BebidaPedidoDTO {
    private Long bebidaId;
    private List<Long> ingredientesExtras;
    private int cantidad;
}
