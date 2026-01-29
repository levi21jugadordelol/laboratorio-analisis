package com.laboratorio.analisis_clinico.usuario.application.usecase.commands;

import com.laboratorio.analisis_clinico.usuario.application.exception.UsuarioNotFoundException;
import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

public class IniciarSesion {

    private final IUsuarioRepo usuarioRepo;

    public IniciarSesion(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario ejecutar(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Credenciales inválidas.")
                );

        if (!usuario.estaActivo()) {
            throw new UsuarioNotFoundException("Usuario inactivo.");
        }

        return usuario;
    }
}

