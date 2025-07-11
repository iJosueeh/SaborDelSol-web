package com.sabordelsol.backend.services;

import com.sabordelsol.backend.models.entity.IngredienteExtra;
import com.sabordelsol.backend.repository.IngredienteExtraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteExtraService {

    private final IngredienteExtraRepository ingredienteRepo;

    public List<IngredienteExtra> listarTodos() {
        return ingredienteRepo.findAll();
    }

    public Optional<IngredienteExtra> buscarPorId(Long id) {
        return ingredienteRepo.findById(id);
    }

    public IngredienteExtra crear(IngredienteExtra ingrediente) {
        return ingredienteRepo.save(ingrediente);
    }

    public IngredienteExtra actualizar(Long id, IngredienteExtra actualizado) {
        IngredienteExtra existente = ingredienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

        existente.setNombre(actualizado.getNombre());
        existente.setPrecioExtra(actualizado.getPrecioExtra());
        return ingredienteRepo.save(existente);
    }

    public void eliminar(Long id) {
        ingredienteRepo.deleteById(id);
    }

}
