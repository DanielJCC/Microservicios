package com.example.demo.controllers.entities;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "usuarios")
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String password;
    private String rep_password;
    private Boolean enabled;
    private String foto;
    private String rol;
    @Temporal(TemporalType.TIME)
    private LocalTime created_at;

    @OneToMany(mappedBy = "usuario")
    private List<Sugerencia> sugerencias;
    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;
    @ManyToMany(mappedBy = "usuarios")
    private List<Partida> partidas;
}