package com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.usuario.domain.enume.EstadoUsuario;
import com.laboratorio.analisis_clinico.usuario.domain.enume.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UsuarioDtoResponse {

    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private RolUsuario rolUsuario;
    private EstadoUsuario estadoUsuario;
    private LocalDateTime fechaCreacion;
}

