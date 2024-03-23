package com.example.demo.dto.usuario;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.example.demo.dto.mensaje.MensajeDTO;
import com.example.demo.dto.sugerencia.SugerenciaDTO;

public record UsuarioDTO(
    UUID id,
    String userName,
    String nombre,
    String apellidos,
    String email,
    Integer edad,
    String foto,
    String rol,
    Boolean enabled,
    LocalTime created_at,
    List<SugerenciaDTO> sugerencias,
    List<MensajeDTO> mensajes
) {
    public List<SugerenciaDTO> sugerencias(){
        return Collections.unmodifiableList(sugerencias);
    }
    public List<MensajeDTO> mensajes(){
        return Collections.unmodifiableList(mensajes);
    }
}
