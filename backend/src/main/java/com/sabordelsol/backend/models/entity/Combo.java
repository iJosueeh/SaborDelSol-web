package com.sabordelsol.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "combos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    private String nombre;
    private String descripcion;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double precio;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
    private List<ComboItem> items;

}
