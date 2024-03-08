package com.example.demo.repository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.entities.Partida;
import com.example.demo.repository.PartidaRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class PartidaRepositoryTest extends AbstractIntegrationDBTest{
    PartidaRepository partidaRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository, UsuarioRepository usuarioRepository) {
        this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("[CREATE] Dado una nueva partida, cuando se guarda, debe persistirse en la base de datos")
    void givenNewPartida_whenSave_thenPartidaIsPersisted() {

        Usuario usuario1 = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Diaz")
                .userName("juanDiaz")
                .password("password123")
                .build()
        );

        Usuario usuario2 = usuarioRepository.save(Usuario.builder()
                .nombre("Gian")
                .apellidos("Astori")
                .userName("gianitaliano")
                .password("password456")
                .build()
        );

        Partida nuevaPartida = Partida.builder()
                .creador("Daniel Cogollos")
                .deporte("Fútbol")
                .ciudad("Santa Marta")
                .provincia("Unimagdalena")
                .fecha(LocalDate.now())
                .hora_comienzo(LocalTime.now())
                .hora_final(LocalTime.now().plusHours(2))
                .participantes(10)
                .suplentes(2)
                .comentarios("Partida amistosa de fútbol")
                .usuarios(List.of(usuario1, usuario2))
                .build();

        Partida partidaSaved = partidaRepository.save(nuevaPartida);

        assertThat(partidaSaved.getId()).isNotNull();
        assertThat(partidaSaved.getCreador()).isEqualTo("Daniel Cogollos");
        assertThat(partidaSaved.getUsuarios()).containsExactlyInAnyOrder(usuario1, usuario2);
    }

    @Test
    @DisplayName("[READ] Dado una partida existente, cuando se busca por su ID, debe retornarla correctamente")
    void givenExistingPartida_whenFindById_thenPartidaIsReturned() {

        Partida partida = partidaRepository.save(Partida.builder()
                .creador("Juan Diaz")
                .deporte("Tenis")
                .ciudad("Zona bananera")
                .provincia("Rio Frio")
                .fecha(LocalDate.now().plusDays(7))
                .hora_comienzo(LocalTime.now())
                .hora_final(LocalTime.now().plusHours(2))
                .participantes(2)
                .suplentes(2)
                .comentarios("Partida de baloncesto en Barcelona")
                .build()
        );

        Optional<Partida> partidaEncontrada = partidaRepository.findById(partida.getId());

        assertThat(partidaEncontrada).isPresent();
        assertThat(partidaEncontrada.get().getCreador()).isEqualTo("Juan Diaz");
        assertThat(partidaEncontrada.get().getDeporte()).isEqualTo("Tenis");
        assertThat(partidaEncontrada.get().getCiudad()).isEqualTo("Zona bananera");
    }
    
    @Test
    @DisplayName("[UPDATE] Dado una partida existente, cuando se actualiza su información, debe reflejarse en la base de datos")
    void givenExistingPartida_whenUpdate_thenPartidaIsUpdated() {

        Partida partida = partidaRepository.save(Partida.builder()
                .creador("Gian Marco")
                .deporte("Jueg de mesa")
                .ciudad("Barranquilla")
                .provincia("El norte")
                .fecha(LocalDate.now().plusDays(14))
                .hora_comienzo(LocalTime.now())
                .hora_final(LocalTime.now().plusHours(3))
                .participantes(100)
                .suplentes(0)
                .comentarios("partida del juego de mesa de el bosque encantado")
                .build()
        );

        partida.setDeporte("Juego de mesa");
        partida.setParticipantes(10);
        Partida partidaActualizada = partidaRepository.save(partida);

        assertThat(partidaActualizada.getDeporte()).isEqualTo("Juego de mesa");
        assertThat(partidaActualizada.getParticipantes()).isEqualTo(10);
    }


    @Test
    @DisplayName("[DELETE] Dado una partida existente, cuando se elimina, no debe estar presente en la base de datos")
    void givenExistingPartida_whenDelete_thenPartidaIsNotPresent() {

        Partida partida = partidaRepository.save(Partida.builder()
                .creador("Daniel Cogollos")
                .deporte("Paintball")
                .ciudad("Santa marta")
                .provincia("Maria Eugenia")
                .fecha(LocalDate.now().plusMonths(1))
                .hora_comienzo(LocalTime.now())
                .hora_final(LocalTime.now().plusHours(2))
                .participantes(120)
                .suplentes(7)
                .comentarios("Partida modo surpervivencia")
                .build()
        );

        partidaRepository.deleteById(partida.getId());
        Optional<Partida> partidaEliminada = partidaRepository.findById(partida.getId());

        assertThat(partidaEliminada).isNotPresent();
    }

}
