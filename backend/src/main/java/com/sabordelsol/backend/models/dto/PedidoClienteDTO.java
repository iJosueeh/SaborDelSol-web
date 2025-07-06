package com.sabordelsol.backend.models.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PedidoClienteDTO {
    private Long id;
    private LocalDateTime fecha;
    private double total;
    private String estado;
    private List<DetallePedidoDTO> detalles;
}
