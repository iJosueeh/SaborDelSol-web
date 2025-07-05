package com.sabordelsol.backend.repository;

import com.sabordelsol.backend.models.entity.Bebida;
import com.sabordelsol.backend.models.entity.Combo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComboRepository extends JpaRepository<Combo, Long> {
}
