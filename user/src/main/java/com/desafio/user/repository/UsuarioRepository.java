package com.desafio.user.repository;
import com.desafio.user.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    List<Usuario> findByNameContainingIgnoreCase(final String nome);
}


