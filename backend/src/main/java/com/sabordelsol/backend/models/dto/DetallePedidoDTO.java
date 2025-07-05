package com.sabordelsol.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetallePedidoDTO {

    private Long bebidaId;
    private String nombreBebida;
    private int cantidad;
    private double subtotal;
    private String descripcionPersonalizada;

}
