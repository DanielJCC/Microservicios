package com.example.demo.dto.usuario;

import java.time.LocalTime;
import java.util.UUID;


public record UsuarioToSaveDTO(
    UUID id,
    String userName,
    String email,
    String nombre,
    String apellidos,
    Integer edad,
    String password,
    String rep_password,
    Boolean enabled,
    String foto,
    String rol,
    LocalTime created_at
) {

}
