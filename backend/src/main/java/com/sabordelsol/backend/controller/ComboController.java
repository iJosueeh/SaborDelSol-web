package com.sabordelsol.backend.controller;

import com.sabordelsol.backend.models.entity.Combo;
import com.sabordelsol.backend.services.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ComboController {

    private final ComboService comboService;

    @PostMapping
    public ResponseEntity<Combo> crear(@RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.guardarCombo(combo));
    }

    @GetMapping
    public List<Combo> listar() {
        return comboService.listarCombos();
    }

}
