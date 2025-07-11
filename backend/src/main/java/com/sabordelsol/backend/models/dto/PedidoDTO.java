package com.sabordelsol.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private double total;
    private String estado;
    private List<DetallePedidoDTO> detalles;
}
