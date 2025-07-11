package com.sabordelsol.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "combo_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComboItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    private String tipoItem;
    @Column(name = "item_id", columnDefinition = "INT")
    private Long itemId;

}
