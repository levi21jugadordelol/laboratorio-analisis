package com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FormatoAnalisisDtoRequest {

    @NotNull
    private Long analisisId;

    @NotBlank
    @Size(max = 150)
    private String nombreFormato;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Map<String, Object> estructuraFormato;

    @NotNull
    private Long createdByUsuario;
}
