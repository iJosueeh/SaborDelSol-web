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
public class PedidoRequestDTO {
    private Long usuarioId;
    private List<BebidaPedidoDTO> bebidas;
    private List<Long> combos;
}