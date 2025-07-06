package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.models.entity.DetallePedido;
import com.sabordelsol.backend.models.entity.Pedido;
import com.sabordelsol.backend.models.entity.Usuario;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReordenarPedidoCommand implements PedidoCommand {

    private final Pedido nuevoPedido;
    private final List<DetallePedido> detallesAnteriores;
    private final Usuario usuario;

    @Override
    public void ejecutar() {
        double total = 0.0;

        for (DetallePedido d : detallesAnteriores) {
            Bebida bebida = d.getBebida();
            double precioBase = bebida.getPrecioBase();

            double subtotal = precioBase * d.getCantidad();
            total += subtotal;

            DetallePedido nuevoDetalle = DetallePedido.builder()
                    .pedido(nuevoPedido)
                    .bebida(bebida)
                    .cantidad(d.getCantidad())
                    .subtotal(subtotal)
                    .descripcionPersonalizada(d.getDescripcionPersonalizada())
                    .build();

            nuevoPedido.getDetalles().add(nuevoDetalle);
        }

        nuevoPedido.setTotal(total);
    }
}
