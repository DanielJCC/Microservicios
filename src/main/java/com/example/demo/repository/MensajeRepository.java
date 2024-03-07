package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje,UUID>{
    List<Mensaje> findByCreador(String creador);
    List<Mensaje> findByCreadorAndDestinatario(String creador, String destinatario);
    List<Mensaje> findByContenidoLike(String contenido);
}
