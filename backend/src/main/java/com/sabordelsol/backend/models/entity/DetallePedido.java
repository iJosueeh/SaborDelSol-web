package com.sabordelsol.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "bebida_id")
    private Bebida bebida;

    private Integer cantidad;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double subtotal;

    @Column(length = 200, nullable = true)
    private String descripcionPersonalizada;

}
