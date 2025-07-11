package com.sabordelsol.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promociones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    private String titulo;

    private String descripcion;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double descuento;

    private Boolean activa;

    private String tipo;

}
