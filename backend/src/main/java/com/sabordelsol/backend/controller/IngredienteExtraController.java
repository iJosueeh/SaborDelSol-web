package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.models.entity.IngredienteExtra;
import com.sabordelsol.backend.services.IngredienteExtraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IngredienteExtraController {

    private final IngredienteExtraService ingredienteService;

    @GetMapping
    public List<IngredienteExtra> listar() {
        return ingredienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteExtra> buscar(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IngredienteExtra> crear(@RequestBody IngredienteExtra ingrediente) {
        return ResponseEntity.ok(ingredienteService.crear(ingrediente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteExtra> actualizar(@PathVariable Long id,
                                                       @RequestBody IngredienteExtra ingrediente) {
        return ResponseEntity.ok(ingredienteService.actualizar(id, ingrediente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ingredienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
