package com.example.demo.controllers.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controllers.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje,UUID>{
    List<Mensaje> findByCreador(String creador);
    List<Mensaje> findByCreadorAndDestinatario(String creador, String destinatario);
    List<Mensaje> findByContenidoLike(String contenido);
}
