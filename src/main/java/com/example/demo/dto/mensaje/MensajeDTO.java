package com.example.demo.dto.mensaje;

import java.time.LocalTime;
import java.util.UUID;

import com.example.demo.dto.usuario.UsuarioDTO;

public record MensajeDTO(
    UUID id,
    String creador,
    String destinatario,
    LocalTime created_at,
    String contenido,
    UsuarioDTO usuario
) {

}
