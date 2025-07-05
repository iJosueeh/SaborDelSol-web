package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelarPedidoCommand implements PedidoCommand {

    private final Pedido pedido;

    @Override
    public void ejecutar() {
        if (pedido.getEstado().equals("ENTREGADO")) {
            throw new RuntimeException("No se puede cancelar un pedido entregado");
        }
        pedido.setEstado("CANCELADO");
        System.out.println("Pedido cancelado: ID " + pedido.getId());
    }
}
