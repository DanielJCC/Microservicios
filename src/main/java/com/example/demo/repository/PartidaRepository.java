package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Partida;

import java.time.LocalDate;
import java.util.List;


public interface PartidaRepository extends JpaRepository<Partida, UUID>{
    List<Partida> findByCiudad(String ciudad);
    List<Partida> findByDeporteNot(String deporte);
    List<Partida> findByCreadorAndFecha(String creador, LocalDate fecha);
}
