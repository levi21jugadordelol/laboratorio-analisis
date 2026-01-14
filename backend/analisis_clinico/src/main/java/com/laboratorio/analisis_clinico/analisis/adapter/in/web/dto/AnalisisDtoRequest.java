package com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnalisisDtoRequest {
    @NotBlank
    @Size(max = 150)
    private String nombreAnalisis;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Long areaMedicaId;
}
