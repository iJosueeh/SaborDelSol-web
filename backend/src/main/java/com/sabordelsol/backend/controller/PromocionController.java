package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.models.entity.Promocion;
import com.sabordelsol.backend.services.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @GetMapping
    public ResponseEntity<List<Promocion>> listarTodas() {
        return ResponseEntity.ok(promocionService.listarTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Promocion>> listarActivas() {
        return ResponseEntity.ok(promocionService.listarActivas());
    }

    @PostMapping
    public ResponseEntity<Promocion> crear(@RequestBody Promocion promocion) {
        return ResponseEntity.ok(promocionService.crear(promocion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizar(@PathVariable Long id, @RequestBody Promocion promocion) {
        return ResponseEntity.ok(promocionService.actualizar(id, promocion));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Promocion> cambiarEstado(@PathVariable Long id, @RequestParam boolean activa) {
        return ResponseEntity.ok(promocionService.cambiarEstado(id, activa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        promocionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
