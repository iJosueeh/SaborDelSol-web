package com.sabordelsol.backend.services;

import com.sabordelsol.backend.models.entity.Combo;
import com.sabordelsol.backend.models.entity.ComboItem;
import com.sabordelsol.backend.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {

    private final ComboRepository comboRepo;

    public Combo guardarCombo(Combo combo) {
        for (ComboItem item : combo.getItems()) {
            item.setCombo(combo);
        }

        return comboRepo.save(combo);
    }

    public List<Combo> listarCombos() {
        return comboRepo.findAll();
    }

}
