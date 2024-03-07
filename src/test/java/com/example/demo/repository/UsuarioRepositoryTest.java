package com.example.demo.repository;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class UsuarioRepositoryTest extends AbstractIntegrationDBTest {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Test
    @DisplayName("[CREATE] Dado un nuevo usuario, cuando se guarda, debe persistirse en la base de datos")
    void givenNewUser_whenSave_thenUserIsPersisted() {

        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Maria")
                .apellidos("García")
                .username("mariagarcia")
                .password("password456")
                .build()
        );

        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("mariagarcia");
    }

    @Test
    @DisplayName("[READ] Dado un usuario existente, cuando se busca por su nombre y apellidos, debe retornar una lista con ese usuario")
    void givenExistingUser_whenFindByNombreAndApellidos_thenUserIsReturned() {

        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Maria")
                .apellidos("García")
                .username("mariagarcia")
                .password("password456")
                .build()
        );

        List<Usuario> usuariosEncontrados = usuarioRepository.findByNombreAndApellidos(user.getNombre(), user.getApellidos());

        assertThat(usuariosEncontrados).isNotEmpty();
        assertThat(usuariosEncontrados).hasSize(1);
        assertThat(usuariosEncontrados.get(0).getNombre()).isEqualTo("Maria");
        assertThat(usuariosEncontrados.get(0).getApellidos()).isEqualTo("García");
    }

    @Test
    @DisplayName("[UPDATE] Dado un usuario existente, cuando se actualiza su información, debe reflejarse en la base de datos")
    void givenExistingUser_whenUpdate_thenUserIsUpdated() {

        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Pedro")
                .apellidos("Ramírez")
                .username("pedroramirez")
                .password("password789")
                .build()
        );

        user.setNombre("Juan");
        user.setApellidos("Diaz Guerrero");
        Usuario userActualizado = usuarioRepository.save(user);

        assertThat(userActualizado.getNombre()).isEqualTo("Juan");
        assertThat(userActualizado.getApellidos()).isEqualTo("Diaz Guerrero");
    }

    @Test
    @DisplayName("[DELETE] Dado un usuario existente, cuando se elimina, no debe estar presente en la base de datos")
    void givenExistingUser_whenDelete_thenUserIsNotPresent() {

        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Ana")
                .apellidos("López")
                .username("analopez")
                .password("password012")
                .build()
        );

        usuarioRepository.delete(user);

        Optional<Usuario> userEliminado = usuarioRepository.findById(user.getId());

        assertThat(userEliminado).isNotPresent();
    }

}
