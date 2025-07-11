package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Pedido;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ConfirmarPedidoCommand implements PedidoCommand {

    private final Pedido pedido;

    @Override
    public void ejecutar() {
        pedido.setEstado("CONFIRMADO");
        pedido.setFecha(LocalDateTime.now());
        System.out.println("Pedido confirmado correctamente: ID " + pedido.getId());
    }
}
