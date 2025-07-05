package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.models.dto.BebidaRequestDTO;
import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.services.BebidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BebidaController {

    private final BebidaService bebidaService;

    @GetMapping
    public List<Bebida> listar() {
        return bebidaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bebida> buscar(@PathVariable Long id) {
        return bebidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bebida> crear(
            @RequestBody BebidaRequestDTO request
    ) {
        Bebida nueva = bebidaService.crear(request.toEntity(), request.getIngredientes());
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> actualizar(
            @PathVariable Long id,
            @RequestBody BebidaRequestDTO request
    ) {
        Bebida actualizada = bebidaService.actualizar(id, request.toEntity(), request.getIngredientes());
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bebidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
