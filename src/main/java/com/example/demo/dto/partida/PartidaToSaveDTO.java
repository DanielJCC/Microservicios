package com.example.demo.dto.partida;

import java.time.LocalDate;
import java.time.LocalTime;
// import java.util.List;
import java.util.UUID;

// import com.example.demo.entities.Usuario;

public record PartidaToSaveDTO(
    UUID id,
    String creador,
    String deporte,
    String ciudad,
    String provincia,
    LocalDate fecha,
    LocalTime hora_comienzo,
    LocalTime hora_final,
    Integer participantes,
    Integer suplentes,
    String comentarios
) {

}
