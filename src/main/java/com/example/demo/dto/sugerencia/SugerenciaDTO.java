package com.example.demo.dto.sugerencia;

import java.time.LocalTime;
import java.util.UUID;

import com.example.demo.dto.usuario.UsuarioDTO;

public record SugerenciaDTO(
    UUID id,
    String description,
    LocalTime created_at,
    UsuarioDTO usuario
) {

}
