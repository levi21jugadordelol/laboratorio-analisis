package com.laboratorio.analisis_clinico.usuario.application.port.out;

import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepo {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAll();   // 👈 necesario

    void save(Usuario usuario);
}


