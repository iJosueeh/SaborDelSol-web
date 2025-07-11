package com.sabordelsol.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bebidas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    private String nombre;

    private String tipo; // ej: jugo, batido, etc.

    @Column(name = "precio_base", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double precioBase;

    private Boolean estado;

    @ManyToMany
    @JoinTable(
            name = "bebida_ingrediente",
            joinColumns = @JoinColumn(name = "bebida_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<IngredienteExtra> ingredientesExtras = new HashSet<>();

}
