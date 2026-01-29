package com.laboratorio.analisis_clinico.usuario.application.usecase.Queries;

import com.laboratorio.analisis_clinico.usuario.application.exception.UsuarioNotFoundException;
import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

public class ValidarRolUsuario {

    private final IUsuarioRepo usuarioRepo;

    public ValidarRolUsuario(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean esAdmin(Long usuarioId) {
        Usuario usuario = obtener(usuarioId);
        return usuario.esAdmin();
    }

    public boolean esLaboratorista(Long usuarioId) {
        Usuario usuario = obtener(usuarioId);
        return usuario.esLaboratorista();
    }

    private Usuario obtener(Long usuarioId) {
        return usuarioRepo.findById(usuarioId)
                .orElseThrow(() ->
                        new UsuarioNotFoundException("Usuario no encontrado.")
                );
    }
}

