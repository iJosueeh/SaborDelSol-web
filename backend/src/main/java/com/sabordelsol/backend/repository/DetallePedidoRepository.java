package com.sabordelsol.backend.repository;

import com.sabordelsol.backend.models.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    void deleteAllByPedidoIdAndBebidaIdAndCantidad(Long pedidoId, Long bebidaId, int cantidad);
}
