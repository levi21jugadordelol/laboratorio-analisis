package com.laboratorio.analisis_clinico.usuario.application.usecase.commands;

import com.laboratorio.analisis_clinico.usuario.application.exception.UsuarioNotFoundException;
import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

public class ActivarDesactivarUsuario {

    private final IUsuarioRepo usuarioRepo;

    public ActivarDesactivarUsuario(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void activar(Long usuarioId) {
        Usuario usuario = obtener(usuarioId);
        usuario.activar();
        usuarioRepo.save(usuario);
    }

    public void desactivar(Long usuarioId) {
        Usuario usuario = obtener(usuarioId);
        usuario.desactivar();
        usuarioRepo.save(usuario);
    }

    private Usuario obtener(Long usuarioId) {
        return usuarioRepo.findById(usuarioId)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Usuario no encontrado.")
                );
    }
}

