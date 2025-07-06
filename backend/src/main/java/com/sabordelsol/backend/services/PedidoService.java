package com.sabordelsol.backend.services;

import com.sabordelsol.backend.command.concrete.*;
import com.sabordelsol.backend.command.executor.PedidoInvoker;
import com.sabordelsol.backend.command.interfaces.PedidoCommand;
import com.sabordelsol.backend.decorator.BebidaBase;
import com.sabordelsol.backend.decorator.BebidaConcreta;
import com.sabordelsol.backend.decorator.IngredienteExtraDecorator;
import com.sabordelsol.backend.factory.ComboFactory;
import com.sabordelsol.backend.factory.ComboFactoryResolver;
import com.sabordelsol.backend.factory.ComboProducto;
import com.sabordelsol.backend.models.dto.*;
import com.sabordelsol.backend.models.entity.*;
import com.sabordelsol.backend.models.strategy.DescuentoFijoStrategy;
import com.sabordelsol.backend.models.strategy.DescuentoPorcentualStrategy;
import com.sabordelsol.backend.models.strategy.PromocionStrategy;
import com.sabordelsol.backend.models.strategy.SinPromocionStrategy;
import com.sabordelsol.backend.observer.PedidoNotifier;
import com.sabordelsol.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final UsuarioRepository usuarioRepo;
    private final BebidaRepository bebidaRepo;
    private final IngredienteExtraRepository ingredienteRepo;
    private final PedidoNotifier pedidoNotifier;
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

            // Decorator aplicado
            BebidaBase bebidaFinal = new BebidaConcreta(bebida);

            if (b.getIngredientesExtras() != null) {
                for (Long idExtra : b.getIngredientesExtras()) {
                    IngredienteExtra extra = ingredienteRepo.findById(idExtra)
                            .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
                    bebidaFinal = new IngredienteExtraDecorator(bebidaFinal, extra);
                }
            }

            double precioFinal = bebidaFinal.getPrecio();
            double subtotal = precioFinal * b.getCantidad();
            String descripcionFinal = bebidaFinal.getDescripcion();

            PedidoCommand comando = new AgregarProductoCommand(pedido, bebida, b.getCantidad(), precioFinal, descripcionFinal);
            invoker.ejecutar(comando);

            System.out.println("Pedido: " + bebidaFinal.getDescripcion() + " | Total por unidad: S/ " + precioFinal);
        }

        // Aplicar promoción si hay (Strategy)
        PromocionStrategy estrategia;

        List<Promocion> promociones = promoRepo.findByActivaTrue();
        if (!promociones.isEmpty()) {
            Promocion promo = promociones.getFirst();
            if (promo.getTipo().equalsIgnoreCase("PORCENTAJE")) {
                estrategia = new DescuentoPorcentualStrategy(promo.getDescuento());
            } else if (promo.getTipo().equalsIgnoreCase("FIJO")) {
                estrategia = new DescuentoFijoStrategy(promo.getDescuento());
            } else {
                estrategia = new SinPromocionStrategy();
            }
        } else {
            estrategia = new SinPromocionStrategy();
        }

        estrategia.aplicar(pedido);

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

    public List<PedidoDTO> listarPedidosPorUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepo.findByUsuarioId(usuarioId);

        return pedidos.stream().map(pedido -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setId(pedido.getId());
            dto.setFecha(pedido.getFecha());
            dto.setEstado(pedido.getEstado());
            dto.setTotal(pedido.getTotal());

            List<DetallePedidoDTO> detalles = pedido.getDetalles().stream().map(detalle -> {
                DetallePedidoDTO d = new DetallePedidoDTO();
                d.setBebidaId(detalle.getBebida().getId());
                d.setNombreBebida(detalle.getBebida().getNombre());
                d.setCantidad(detalle.getCantidad());
                d.setSubtotal(detalle.getSubtotal());
                d.setDescripcionPersonalizada(detalle.getDescripcionPersonalizada());
                return d;
            }).toList();

            dto.setDetalles(detalles);
            return dto;
        }).toList();
    }

    public DashboardAdminDTO getDashboardAdmin() {
        int totalPedidos = pedidoRepo.findAll().size();
        double totalIngresos = pedidoRepo.findAll().stream()
                .mapToDouble(Pedido::getTotal).sum();

        Map<String, Long> pedidosPorEstado = pedidoRepo.findAll().stream()
                .collect(Collectors.groupingBy(Pedido::getEstado, Collectors.counting()));

        Map<String, Long> bebidasMasVendidas = detalleRepo.findAll().stream()
                .collect(Collectors.groupingBy(
                        d -> d.getBebida().getNombre(),
                        Collectors.summingLong(DetallePedido::getCantidad)
                ));

        int promocionesActivas = promoRepo.findByActivaTrue().size();

        return DashboardAdminDTO.builder()
                .totalPedidos(totalPedidos)
                .totalIngresos(totalIngresos)
                .pedidosPorEstado(pedidosPorEstado)
                .bebidasMasVendidas(bebidasMasVendidas)
                .promocionesActivas(promocionesActivas)
                .build();
    }

    public List<PedidoClienteDTO> listarPedidosDeCliente(String correo) {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Pedido> pedidos = pedidoRepo.findByUsuarioId(usuario.getId());

        return pedidos.stream().map(pedido -> {
            PedidoClienteDTO dto = new PedidoClienteDTO();
            dto.setId(pedido.getId());
            dto.setFecha(pedido.getFecha());
            dto.setEstado(pedido.getEstado());
            dto.setTotal(pedido.getTotal());

            List<DetallePedidoDTO> detalles = pedido.getDetalles().stream().map(detalle -> {
                DetallePedidoDTO d = new DetallePedidoDTO();
                d.setBebidaId(detalle.getBebida().getId());
                d.setNombreBebida(detalle.getBebida().getNombre());
                d.setCantidad(detalle.getCantidad());
                d.setSubtotal(detalle.getSubtotal());
                d.setDescripcionPersonalizada(detalle.getDescripcionPersonalizada());
                return d;
            }).toList();

            dto.setDetalles(detalles);
            return dto;
        }).toList();
    }

    public Pedido reordenarPedido(Long pedidoIdAnterior, String correo) {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido anterior = pedidoRepo.findById(pedidoIdAnterior)
                .orElseThrow(() -> new RuntimeException("Pedido anterior no encontrado"));

        if (!anterior.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("No puedes reordenar un pedido que no es tuyo");
        }

        Pedido nuevo = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .estado("EN PROCESO")
                .total(0.0)
                .detalles(new ArrayList<>())
                .build();

        // Patrón Command aplicado
        PedidoCommand comando = new ReordenarPedidoCommand(nuevo, anterior.getDetalles(), usuario);
        PedidoInvoker invoker = new PedidoInvoker();
        invoker.ejecutar(comando);

        Pedido guardado = pedidoRepo.save(nuevo);
        for (DetallePedido det : nuevo.getDetalles()) {
            det.setPedido(guardado);
        }
        detalleRepo.saveAll(nuevo.getDetalles());

        return guardado;
    }

    public Pedido crearPedidoDesdeCombo(ComboRequestDTO dto, String correo) {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ComboFactoryResolver resolver = new ComboFactoryResolver();
        ComboFactory factory = resolver.resolver(dto.getCombo());
        ComboProducto combo = factory.crearCombo();

        BebidaPedidoDTO bebidaDTO = new BebidaPedidoDTO();
        bebidaDTO.setBebidaId(combo.getBebidaId());
        bebidaDTO.setIngredientesExtras(combo.getIngredientesExtraIds());
        bebidaDTO.setCantidad(dto.getCantidad());

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .estado("EN PROCESO")
                .total(0.0)
                .detalles(new ArrayList<>())
                .build();

        Bebida bebida = bebidaRepo.findById(bebidaDTO.getBebidaId())
                .orElseThrow(() -> new RuntimeException("Bebida no encontrada"));

        BebidaBase bebidaFinal = new BebidaConcreta(bebida);
        if (bebidaDTO.getIngredientesExtras() != null) {
            for (Long idExtra : bebidaDTO.getIngredientesExtras()) {
                IngredienteExtra extra = ingredienteRepo.findById(idExtra)
                        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
                bebidaFinal = new IngredienteExtraDecorator(bebidaFinal, extra);
            }
        }

        double precioFinal = bebidaFinal.getPrecio();
        String descripcion = bebidaFinal.getDescripcion();

        PedidoInvoker invoker = new PedidoInvoker();
        PedidoCommand cmd = new AgregarProductoCommand(pedido, bebida, bebidaDTO.getCantidad(), precioFinal, descripcion);
        invoker.ejecutar(cmd);

        // Guardar
        Pedido guardado = pedidoRepo.save(pedido);
        for (DetallePedido d : pedido.getDetalles()) {
            d.setPedido(guardado);
        }
        detalleRepo.saveAll(pedido.getDetalles());

        return guardado;
    }

    public Pedido actualizarEstado(Long pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        Pedido actualizado = pedidoRepo.save(pedido);

        // Notificar SSE solo al cliente dueño del pedido
        String mensaje = "Tu pedido #" + pedido.getId() + " ahora está en estado: " + nuevoEstado;
        pedidoNotifier.notificarA(pedido.getUsuario().getId(), mensaje);

        return actualizado;
    }


}
