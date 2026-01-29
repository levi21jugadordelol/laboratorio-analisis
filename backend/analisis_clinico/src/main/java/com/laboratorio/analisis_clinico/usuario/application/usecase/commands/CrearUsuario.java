package com.laboratorio.analisis_clinico.usuario.application.usecase.commands;

import com.laboratorio.analisis_clinico.usuario.application.exception.UsuarioNotFoundException;
import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import com.laboratorio.analisis_clinico.usuario.domain.enume.RolUsuario;

public class CrearUsuario {

    private final IUsuarioRepo usuarioRepo;

    public CrearUsuario(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void ejecutar(
            String nombreUsuario,
            String email,
            RolUsuario rolUsuario
    ) {
        usuarioRepo.findByEmail(email).ifPresent(u -> {
            throw new UsuarioNotFoundException("El email ya está registrado.");
        });

        Usuario usuario = new Usuario(
                nombreUsuario,
                email,
                rolUsuario
        );

        usuarioRepo.save(usuario);
    }
}

