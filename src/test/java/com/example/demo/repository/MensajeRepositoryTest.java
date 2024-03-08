package com.example.demo.repository;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Sugerencia;
import com.example.demo.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.entities.Mensaje;
import org.testcontainers.shaded.org.apache.commons.lang3.ObjectUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;

public class MensajeRepositoryTest extends AbstractIntegrationDBTest{
    private MensajeRepository mensajeRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        mensajeRepository.deleteAll();
    }
    @Test
    @DisplayName("[CREATE] Dado un nuevo mensaje, cuando se crea, debe estar presente en la base de datos")
    void givenNewMensaje_whenSave_thenMensajeIsPersisted(){
        //given
        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Guerrero")
                .userName("juanG")
                .password("password123")
                .build()
        );

        Mensaje mensaje = Mensaje.builder()
                .creador("Juan")
                .destinatario("Marcelo")
                .created_at(LocalTime.now())
                .contenido("Agachate y conocelo")
                .usuario(user)
                .build();
        //when
        Mensaje messageSaved = mensajeRepository.save(mensaje);

        //then
        assertThat(messageSaved.getId()).isNotNull();
        assertThat(messageSaved.getUsuario()).isEqualTo(user);
    }

    @Test
    @DisplayName("[READ] dado el nombre de un creador, los mensajes de dicho creador se pueden leer de la base de datos")
    void givenCreador_whenFindByCreador_thenMensajeIsReturned(){
        //given
        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Guerrero")
                .userName("juanG")
                .password("password123")
                .build()
        );

        Mensaje mensaje = Mensaje.builder()
                .creador("Juan")
                .destinatario("Marcelo")
                .created_at(LocalTime.now())
                .contenido("Agachate y conocelo")
                .usuario(user)
                .build();
        Mensaje messageSaved = mensajeRepository.save(mensaje);
        //when
        List <Mensaje> mensajes=mensajeRepository.findByCreador(mensaje.getCreador());

        //then
        assertThat(mensajes).isNotNull();
        assertThat(mensajes.get(0).getUsuario()).isEqualTo(user);
    }

    @Test
    @DisplayName("[UPDATE] dado un cambio en el mensaje, los cambios deben de ser visualizados en la base de datos")
    void givenExistingMensaje_whenMensajeUpdated_thenMensajeIsUpdated(){
        //given
        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Guerrero")
                .userName("juanG")
                .password("password123")
                .build()
        );

        Mensaje mensaje = Mensaje.builder()
                .creador("Juan")
                .destinatario("Marcelo")
                .created_at(LocalTime.now())
                .contenido("Agachate y conocelo")
                .usuario(user)
                .build();
        Mensaje messageSaved = mensajeRepository.save(mensaje);
        //when
        String oldDest= mensaje.getDestinatario();
        String oldCont= mensaje.getContenido();
        messageSaved.setDestinatario("Rodrigo");
        messageSaved.setContenido("Programa y te lo digo");

        //then
        assertThat(messageSaved.getDestinatario()).isNotEqualTo(oldDest);
        assertThat(messageSaved.getContenido()).isNotEqualTo(oldCont);
        assertThat(messageSaved).isNotNull();
    }

    @Test
    @DisplayName("[DELETE] dado la eliminación de un mensaje existente, este no debe de ser visualizado en la base de datos")
    void givenExistingID_whenMensajeDeleted_thenMensajeIsNotPresent(){
        //given
        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Guerrero")
                .userName("juanG")
                .password("password123")
                .build()
        );

        Mensaje mensaje = Mensaje.builder()
                .creador("Juan")
                .destinatario("Marcelo")
                .created_at(LocalTime.now())
                .contenido("Agachate y conocelo")
                .usuario(user)
                .build();
        Mensaje messageSaved = mensajeRepository.save(mensaje);
        //when
        mensajeRepository.delete(messageSaved);
        Optional<Mensaje> mensajeEliminado = mensajeRepository.findById(mensaje.getId());
        //then
        assertThat(mensajeEliminado).isNotPresent();
        assertThat(mensaje).isNotNull();
    }
}
