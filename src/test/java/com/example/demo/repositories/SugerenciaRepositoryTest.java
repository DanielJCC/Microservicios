package com.example.demo.repositories;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Sugerencia;

public class SugerenciaRepositoryTest extends AbstractIntegrationDBTest{
    SugerenciaRepository sugerenciaRepository;

    @Autowired
    public SugerenciaRepositoryTest(SugerenciaRepository sugerenciaRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
    }
    @BeforeEach
    void setUp() {
        sugerenciaRepository.deleteAll();
    }
    @Test
    void givenAnSugerencia_whenSave_thenSugerenciawithId(){
        //given
        Sugerencia mensaje = Sugerencia.builder()
                .description("Se sugiere que mejoren el tiempo en el juego")
                .created_at(LocalTime.now())
                .build();
        //when
        Sugerencia mensajeSaved = sugerenciaRepository.save(mensaje);
        //then
        assertThat(mensajeSaved.getId()).isNotNull();

    }
}
