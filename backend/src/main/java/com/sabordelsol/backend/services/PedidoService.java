package com.sabordelsol.backend.services;

import com.sabordelsol.backend.command.concrete.AgregarProductoCommand;
import com.sabordelsol.backend.command.concrete.CancelarPedidoCommand;
import com.sabordelsol.backend.command.concrete.ConfirmarPedidoCommand;
import com.sabordelsol.backend.command.concrete.QuitarProductoCommand;
import com.sabordelsol.backend.command.executor.PedidoInvoker;
import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.models.dto.PedidoRequestDTO;
import com.sabordelsol.backend.models.entity.*;
import com.sabordelsol.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final ComboRepository comboRepo;
    private final UsuarioRepository usuarioRepo;
    private final BebidaRepository bebidaRepo;
    private final IngredienteExtraRepository ingredienteRepo;
    private final PromocionRepository promoRepo;

    public Pedido crearPedido(PedidoRequestDTO dto) {
        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .estado("EN PROCESO")
                .total(0.0)
                .detalles(new ArrayList<>())
                .build();

        PedidoInvoker invoker = new PedidoInvoker();

        for (var b : dto.getBebidas()) {
            Bebida bebida = bebidaRepo.findById(b.getBebidaId())
                    .orElseThrow(() -> new RuntimeException("Bebida no encontrada"));

            double precioFinal = bebida.getPrecioBase();

            // Calcular precio usando Decorator (simulado)
            if (b.getIngredientesExtras() != null) {
                for (Long ingId : b.getIngredientesExtras()) {
                    IngredienteExtra ing = ingredienteRepo.findById(ingId)
                            .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
                    precioFinal += ing.getPrecioExtra();
                }
            }

            PedidoCommand comando = new AgregarProductoCommand(pedido, bebida, b.getCantidad(), precioFinal);
            invoker.ejecutar(comando);
        }

        // Aplicar promoción (Strategy Pattern)
        List<Promocion> promociones = promoRepo.findByActivaTrue();
        if (!promociones.isEmpty()) {
            double descuento = promociones.getFirst().getDescuento();
            pedido.setTotal(pedido.getTotal() * (1 - descuento / 100.0));
        }

        Pedido guardado = pedidoRepo.save(pedido);
        for (DetallePedido d : pedido.getDetalles()) {
            d.setPedido(guardado);
        }
        detalleRepo.saveAll(pedido.getDetalles());

        return guardado;
    }

    public Pedido quitarProductoDelPedido(Long pedidoId, Long bebidaId, int cantidad) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getEstado().equals("EN PROCESO")) {
            throw new RuntimeException("Solo puedes modificar pedidos en proceso");
        }

        Bebida bebida = bebidaRepo.findById(bebidaId)
                .orElseThrow(() -> new RuntimeException("Bebida no encontrada"));

        PedidoInvoker invoker = new PedidoInvoker();
        PedidoCommand quitar = new QuitarProductoCommand(pedido, bebida, cantidad);
        invoker.ejecutar(quitar);

        // Actualiza los detalles en la base de datos
        detalleRepo.deleteAllByPedidoIdAndBebidaIdAndCantidad(pedidoId, bebidaId, cantidad);

        // Guarda cambios
        pedidoRepo.save(pedido);
        return pedido;
    }

    public Pedido confirmarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getEstado().equals("EN PROCESO")) {
            throw new RuntimeException("Solo puedes confirmar pedidos en proceso");
        }

        PedidoCommand confirmar = new ConfirmarPedidoCommand(pedido);
        PedidoInvoker invoker = new PedidoInvoker();
        invoker.ejecutar(confirmar);

        return pedidoRepo.save(pedido);
    }

    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        PedidoCommand cancelar = new CancelarPedidoCommand(pedido);
        PedidoInvoker invoker = new PedidoInvoker();
        invoker.ejecutar(cancelar);

        return pedidoRepo.save(pedido);
    }

}
