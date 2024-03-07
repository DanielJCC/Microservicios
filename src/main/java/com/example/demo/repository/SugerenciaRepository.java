package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Sugerencia;

public interface SugerenciaRepository extends JpaRepository<Sugerencia,UUID>{
    List<Sugerencia> findByDescriptionContaining(String description);
}
