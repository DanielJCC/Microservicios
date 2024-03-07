package com.example.demo.controllers.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controllers.entities.Sugerencia;

public interface SugerenciaRepository extends JpaRepository<Sugerencia,UUID>{
    List<Sugerencia> findByDescriptionLike(String description);
}
