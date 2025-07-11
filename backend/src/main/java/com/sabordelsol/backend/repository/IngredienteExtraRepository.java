package com.sabordelsol.backend.repository;

import com.sabordelsol.backend.models.entity.IngredienteExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteExtraRepository extends JpaRepository<IngredienteExtra, Long> {
}
