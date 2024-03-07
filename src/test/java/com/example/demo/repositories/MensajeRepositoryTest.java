package com.example.demo.repositories;


import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Mensaje;

import static org.assertj.core.api.Assertions.assertThat;

public class MensajeRepositoryTest extends AbstractIntegrationDBTest{
    MensajeRepository mensajeRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }
    @BeforeEach
    void setUp() {
        mensajeRepository.deleteAll();
    }
    @Test
    void givenAnMensaje_whenSave_thenMensajewithId(){
        //given
        Mensaje mensaje = Mensaje.builder()
                .creador("Julian")
                .destinatario("Cutupley")
                .created_at(LocalTime.now())
                .contenido("Hola Cutupley que tal estás")
                .build();
        //when
        Mensaje messageSaved = mensajeRepository.save(mensaje);
        //then
        assertThat(messageSaved.getId()).isNotNull();

    }
}
