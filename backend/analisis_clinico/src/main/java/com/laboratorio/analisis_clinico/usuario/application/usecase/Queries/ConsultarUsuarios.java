package com.laboratorio.analisis_clinico.usuario.application.usecase.Queries;

import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;

import java.util.List;

public class ConsultarUsuarios {

    private final IUsuarioRepo usuarioRepo;

    public ConsultarUsuarios(IUsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<Usuario> ejecutar() {
        return usuarioRepo.findAll();
    }
}

