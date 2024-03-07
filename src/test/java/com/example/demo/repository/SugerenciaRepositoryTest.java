package com.example.demo.repository;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entities.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.entities.Sugerencia;

import java.util.List;
import java.util.Optional;

public class SugerenciaRepositoryTest extends AbstractIntegrationDBTest{
    private SugerenciaRepository sugerenciaRepository;
    private UsuarioRepository usuarioRepository;


    @Autowired
    public SugerenciaRepositoryTest(SugerenciaRepository sugerenciaRepository, UsuarioRepository usuarioRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }
     @BeforeEach
    void setUp() {
        sugerenciaRepository.deleteAll();
        usuarioRepository.deleteAll();

    }

    @Test
    @DisplayName("[CREATE] Dado una nueva sugerencia, cuando se guarda, debe persistirse en la base de datos")
    void givenNewSugerencia_whenSave_thenSugerenciaIsPersisted() {

        Usuario user = usuarioRepository.save(Usuario.builder()
                .nombre("Juan")
                .apellidos("Pérez")
                .userName("juanperez")
                .password("password123")
                .build()
        );

        Sugerencia sugerenciaSaved = sugerenciaRepository.save(Sugerencia.builder()
                .description("El lugar donde se juega se requiere un techo")
                .usuario(user)
                .build()
        );

        assertThat(sugerenciaSaved.getId()).isNotNull();
        assertThat(sugerenciaSaved.getDescription()).isEqualTo("El lugar donde se juega se requiere un techo");
        assertThat(sugerenciaSaved.getUsuario()).isEqualTo(user);
    }

    @Test
    @DisplayName("[READ] Dado una sugerencia existente, cuando buscamos alguna palabra que haga match con la descripcion debe arrojar resultados")
    void givenExistingSeugerencia_whenSearchForMatchInTheDescription_sugerenciaIsReturned() {

        Usuario usuario = usuarioRepository.save(Usuario.builder()
                .nombre("Maria")
                .apellidos("García")
                .userName("mariagarcia")
                .password("password456")
                .build()
        );

        sugerenciaRepository.save(Sugerencia.builder()
                .description("Las piezas de juegos deberian cambiarlas por unas nuevas")
                .usuario(usuario)
                .build()
        );

        List<Sugerencia> sugerenciaMatch = sugerenciaRepository.findByDescriptionContaining("piezas");

        String descripcion = sugerenciaMatch.get(0).getDescription();
        boolean contieneStringPieza = descripcion.contains("pieza");


        assertThat(sugerenciaMatch).hasSize(1);;
        assertThat(contieneStringPieza).isTrue();
        assertThat(sugerenciaMatch.get(0).getUsuario()).isEqualTo(usuario);
    }

    @Test
    @DisplayName("[UPDATE] Dado una sugerencia existente, cuando se actualiza su descripción, debe reflejarse en la base de datos")
    void givenExistingSugerencia_whenUpdate_thenSugerenciaIsUpdated() {

        Usuario usuario = usuarioRepository.save(Usuario.builder()
                .nombre("Pedro")
                .apellidos("Ramírez")
                .userName("pedroramirez")
                .password("password789")
                .build());

        Sugerencia sugerencia = sugerenciaRepository.save(Sugerencia.builder()
                .description("deberian repartir refrigerios en los jos :(")
                .usuario(usuario)
                .build());

        sugerencia.setDescription("Deberian repartir refrigerios durante los juegos :(");
        Sugerencia sugerenciaActualizada = sugerenciaRepository.save(sugerencia);

        assertThat(sugerenciaActualizada.getDescription()).isEqualTo("Deberian repartir refrigerios durante los juegos :(");
        assertThat(sugerenciaActualizada.getUsuario()).isEqualTo(usuario);
    }

    @Test
    @DisplayName("[DELETE] Dado una sugerencia existente, cuando se elimina, no debe estar presente en la base de datos")
    void givenExistingSugerencia_whenDelete_thenSugerenciaIsNotPresent() {

        Usuario usuario = usuarioRepository.save(Usuario.builder()
                .nombre("Ana")
                .apellidos("López")
                .userName("analopez")
                .password("password012")
                .build()
        );

        Sugerencia sugerencia = sugerenciaRepository.save(Sugerencia.builder()
                .description("Los balones de futbol estan todos rotos y dañados")
                .usuario(usuario)
                .build()
        );

        sugerenciaRepository.delete(sugerencia);

        Optional<Sugerencia> sugerenciaEliminada = sugerenciaRepository.findById(sugerencia.getId());
        List<Sugerencia> sugerenciaMatch = sugerenciaRepository.findByDescriptionContaining("balones");

        assertThat(sugerenciaEliminada).isNotPresent();
        assertThat(sugerenciaMatch).hasSize(0);;

    }

}
