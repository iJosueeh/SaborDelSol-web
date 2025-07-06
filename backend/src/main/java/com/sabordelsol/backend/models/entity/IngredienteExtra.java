package com.sabordelsol.backend.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "ingredientes_extra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredienteExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    private String nombre;

    @Column(name = "precio_extra", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double precioExtra;

    @ManyToMany(mappedBy = "ingredientesExtras")
    private Set<Bebida> bebidas;
}
