package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.models.entity.DetallePedido;
import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class QuitarProductoCommand implements PedidoCommand {

    private final Pedido pedido;
    private final Bebida bebida;
    private final int cantidad;

    @Override
    public void ejecutar() {
        List<DetallePedido> detalles = pedido.getDetalles();
        Iterator<DetallePedido> iterator = detalles.iterator();

        while (iterator.hasNext()) {
            DetallePedido detalle = iterator.next();
            if (detalle.getBebida() != null &&
                    detalle.getBebida().getId().equals(bebida.getId()) &&
                    detalle.getCantidad() == cantidad) {

                pedido.setTotal(pedido.getTotal() - detalle.getSubtotal());
                iterator.remove();
                System.out.println("Producto eliminado del pedido: " + bebida.getNombre());
                break;
            }
        }
    }
}
