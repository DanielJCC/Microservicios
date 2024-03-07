package com.example.demo.controllers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controllers.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
}
