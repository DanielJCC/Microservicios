package com.example.demo.controllers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controllers.entities.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByNombreAndApellidos(String nombre, String apellidos);
    List<Usuario> findByUserNameOrEmail(String userName, String email);
}
