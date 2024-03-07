package com.example.demo.controllers.repositories;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.controllers.entities.Usuario;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioRepositoryTest extends AbstractIntegrationDBTest{
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    void initMockUsuarios(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellidos("Pizarro")
                .userName("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellidos("Pertuz")
                .userName("josepertuz")
                .password("123")
                .build();
        usuarioRepository.save(usuario2);
        usuarioRepository.flush();
    }

    @BeforeEach
    void setUp() {

        usuarioRepository.deleteAll();
        initMockUsuarios();
    }
    @Test
    void givenAnUser_whenSave_thenUserwithId(){
        //given
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellidos("Pizarro")
                .userName("julianpizarro")
                .password("123")
                .build();
        //when
        Usuario userSaved = usuarioRepository.save(usuario);
        //then
        assertThat(userSaved.getId()).isNotNull();

    }
    @Test
    @DisplayName("dado un conjunto de usuarios al buscarlo todos obtenemos la lista de los usuarios en la base de datos")
    void shouldGetAllUsers(){
        //GIVEN
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellidos("Pizarro")
                .userName("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellidos("Pertuz")
                .userName("josepertuz")
                .password("123")
                .build();
        usuarioRepository.save(usuario2);
        usuarioRepository.flush();
        //WHEN
        List<Usuario> usuarios = usuarioRepository.findAll();

        //THEN
        assertThat(usuarios).hasSize(2);
    }
    @Test
    void givenUsuarios_whenBuscarPorNombreyApellido_thenObtienesUnaListaDeUsuarios(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellidos("Pizarro")
                .userName("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);
        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellidos("Pertuz")
                .userName("josepertuz")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        List<Usuario> usuarios = usuarioRepository.findByNombreAndApellidos("Julian", "Pizarro");

        assertThat(usuarios).isNotEmpty();
        assertThat(usuarios).first().hasFieldOrPropertyWithValue("nombre","Julian");
    }
}
