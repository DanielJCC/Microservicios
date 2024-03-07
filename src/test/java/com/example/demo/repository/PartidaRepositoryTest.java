package com.example.demo.repository;


import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.entities.Partida;
import com.example.demo.repository.PartidaRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class PartidaRepositoryTest extends AbstractIntegrationDBTest{
    PartidaRepository partidaRepository;

    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }
    @BeforeEach
    void setUp() {
        partidaRepository.deleteAll();
    }
    @Test
    void givenAnPartida_whenSave_thenPartidawithId(){
        //given
        Partida partida = Partida.builder()
                .creador("Julian")
                .deporte("EAsports")
                .ciudad("Santa Marta")
                .fecha(LocalDate.now())
                .build();
        //when
        Partida partidaSaved = partidaRepository.save(partida);
        //then
        assertThat(partidaSaved.getId()).isNotNull();

    }
}
