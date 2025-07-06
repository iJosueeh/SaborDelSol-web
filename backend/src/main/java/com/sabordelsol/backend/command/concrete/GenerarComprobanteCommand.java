package com.sabordelsol.backend.command.concrete;

import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.entity.Pedido;
import com.sabordelsol.backend.services.PdfService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;

@Getter
@RequiredArgsConstructor
public class GenerarComprobanteCommand implements PedidoCommand {

    private final PdfService pdfService;
    private final Pedido pedido;
    private ByteArrayInputStream resultado;

    @Override
    public void ejecutar() {
        this.resultado = pdfService.generarComprobantePedido(pedido);
    }
}
