package com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResultadoDtoRequest {

    @NotNull
    private Long ordenAnalisisId;

    @NotNull
    private Map<String, Object> resultadoJson;

    @Size(max = 255)
    private String observacion;

    @NotNull
    private Long createdByUsuario;
}

