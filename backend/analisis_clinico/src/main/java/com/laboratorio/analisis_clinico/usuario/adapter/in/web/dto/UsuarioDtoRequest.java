package com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.usuario.domain.enume.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDtoRequest {

    @NotBlank
    @Size(max = 100)
    private String nombreUsuario;

    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @NotNull
    private RolUsuario rolUsuario;
}

