package com.sabordelsol.backend.models.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DashboardAdminDTO {
    private int totalPedidos;
    private double totalIngresos;
    private Map<String, Long> pedidosPorEstado;
    private Map<String, Long> bebidasMasVendidas;
    private int promocionesActivas;

}
