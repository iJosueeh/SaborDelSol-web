package com.sabordelsol.backend.services;

import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.models.entity.IngredienteExtra;
import com.sabordelsol.backend.repository.BebidaRepository;
import com.sabordelsol.backend.repository.IngredienteExtraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BebidaService {

    private final BebidaRepository bebidaRepo;
    private final IngredienteExtraRepository ingredienteRepo;

    public List<Bebida> listarTodas() {
        return bebidaRepo.findAll();
    }

    public Optional<Bebida> buscarPorId(Long id) {
        return bebidaRepo.findById(id);
    }

    public Bebida crear(Bebida bebida, List<Long> idsIngredientes) {
        Set<IngredienteExtra> extras = new HashSet<>(ingredienteRepo.findAllById(idsIngredientes));
        bebida.setIngredientesExtras(extras);
        return bebidaRepo.save(bebida);
    }

    public Bebida actualizar(Long id, Bebida actualizada, List<Long> idsIngredientes) {
        Bebida bebida = bebidaRepo.findById(id).orElseThrow(() -> new RuntimeException("No encontrada"));

        bebida.setNombre(actualizada.getNombre());
        bebida.setTipo(actualizada.getTipo());
        bebida.setPrecioBase(actualizada.getPrecioBase());
        bebida.setEstado(actualizada.getEstado());

        Set<IngredienteExtra> extras = new HashSet<>(ingredienteRepo.findAllById(idsIngredientes));
        bebida.setIngredientesExtras(extras);

        return bebidaRepo.save(bebida);
    }

    public void eliminar(Long id) {
        bebidaRepo.deleteById(id);
    }

}
