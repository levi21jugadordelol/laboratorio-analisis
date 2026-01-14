package com.laboratorio.analisis_clinico.usuario.application.usecase.commands;

import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

public class EditarUsuario {

    private final IUsuarioRepo usuarioRepo;

    public EditarUsuario(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void ejecutar(
            Long usuarioId,
            String nombreUsuario,
            String email
    ) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario no encontrado.")
                );

        usuario.actualizarDatos(nombreUsuario, email);

        usuarioRepo.save(usuario);
    }
}

