package com.sabordelsol.backend.command.executor;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoInvoker {
    private final List<PedidoCommand> historial = new ArrayList<>();

    public void ejecutar(PedidoCommand comando) {
        historial.add(comando);
        comando.ejecutar();
    }

}
