package com.sabordelsol.backend.services;

import com.sabordelsol.backend.models.entity.Promocion;
import com.sabordelsol.backend.observer.PromocionPublisher;
import com.sabordelsol.backend.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository promocionRepo;
    private final PromocionPublisher publisher;

    public List<Promocion> listarTodas() {
        return promocionRepo.findAll();
    }

    public List<Promocion> listarActivas() {
        return promocionRepo.findByActivaTrue();
    }

    public Promocion crear(Promocion promocion) {
        promocion.setActiva(true);
        Promocion nueva = promocionRepo.save(promocion);

        String mensaje = "Nueva promoción: " + nueva.getTitulo() +
                " (" + nueva.getDescuento() + "%)";
        publisher.notificarTodos(mensaje);

        return nueva;
    }

    public Promocion actualizar(Long id, Promocion nuevaPromo) {
        Promocion promo = promocionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

        promo.setTitulo(nuevaPromo.getTitulo());
        promo.setDescripcion(nuevaPromo.getDescripcion());
        promo.setDescuento(nuevaPromo.getDescuento());
        return promocionRepo.save(promo);
    }

    public Promocion cambiarEstado(Long id, boolean activa) {
        Promocion promo = promocionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

        promo.setActiva(activa);
        return promocionRepo.save(promo);
    }

    public void eliminar(Long id) {
        promocionRepo.deleteById(id);
    }

}
