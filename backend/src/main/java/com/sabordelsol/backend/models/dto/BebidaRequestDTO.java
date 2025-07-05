package com.sabordelsol.backend.models.dto;

import com.sabordelsol.backend.models.entity.Bebida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BebidaRequestDTO {
    private String nombre;
    private String tipo;
    private Double precioBase;
    private Boolean estado;
    private List<Long> ingredientes; // IDs de ingredientes extra

    public Bebida toEntity() {
        return Bebida.builder()
                .nombre(nombre)
                .tipo(tipo)
                .precioBase(precioBase)
                .estado(estado)
                .build();
    }
}
