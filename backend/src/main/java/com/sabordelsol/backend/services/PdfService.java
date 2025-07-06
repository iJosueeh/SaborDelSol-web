package com.sabordelsol.backend.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sabordelsol.backend.models.entity.DetallePedido;
import com.sabordelsol.backend.models.entity.Pedido;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayInputStream generarComprobantePedido(Pedido pedido) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Comprobante de Pedido", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Info del pedido
            document.add(new Paragraph("Pedido ID: " + pedido.getId()));
            document.add(new Paragraph("Fecha: " + pedido.getFecha()));
            document.add(new Paragraph("Estado: " + pedido.getEstado()));
            document.add(new Paragraph("Cliente: " + pedido.getUsuario().getNombre()));
            document.add(Chunk.NEWLINE);

            // Tabla de detalles
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 1, 2, 2});

            table.addCell("Descripción");
            table.addCell("Cant.");
            table.addCell("Precio unit.");
            table.addCell("Subtotal");

            for (DetallePedido d : pedido.getDetalles()) {
                table.addCell(d.getDescripcionPersonalizada());
                table.addCell(String.valueOf(d.getCantidad()));
                double precioUnit = d.getSubtotal() / d.getCantidad();
                table.addCell(String.format("S/ %.2f", precioUnit));
                table.addCell(String.format("S/ %.2f", d.getSubtotal()));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            // Total
            Paragraph total = new Paragraph("Total: S/ " + String.format("%.2f", pedido.getTotal()));
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            document.add(total);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error generando PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
