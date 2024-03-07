package com.example.demo.controllers.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "partidas")
@Builder
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String creador;
    private String deporte;
    private String ciudad;
    private String provincia;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @Temporal(TemporalType.TIME)
    private LocalTime hora_comienzo;
    @Temporal(TemporalType.TIME)
    private LocalTime hora_final;
    private Integer participantes;
    private Integer suplentes;
    private String comentarios;

    @ManyToMany()
    @JoinTable(name = "partidas_usuarios", 
        joinColumns = @JoinColumn(name="FK_PARTIDA", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="FK_USUARIO", referencedColumnName = "id"))
    private List<Usuario> usuarios;
}