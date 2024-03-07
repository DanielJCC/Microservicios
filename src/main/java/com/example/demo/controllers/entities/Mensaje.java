package com.example.demo.controllers.entities;

import java.time.LocalTime;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "mensajes")
@Builder
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String creador;
    private String destinatario;
    @Temporal(TemporalType.TIME)
    private LocalTime created_at;
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;
}
