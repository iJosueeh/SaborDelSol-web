package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.models.entity.DetallePedido;
import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgregarProductoCommand implements PedidoCommand {

    private final Pedido pedido;
    private final Bebida bebida;
    private final int cantidad;
    private final double precioFinal;

    @Override
    public void ejecutar() {
        DetallePedido detalle = DetallePedido.builder()
                .pedido(pedido)
                .bebida(bebida)
                .cantidad(cantidad)
                .subtotal(precioFinal * cantidad)
                .build();

        pedido.getDetalles().add(detalle);
        pedido.setTotal(pedido.getTotal() + detalle.getSubtotal());

        System.out.println("Producto agregado al pedido: " + bebida.getNombre());
    }
}
