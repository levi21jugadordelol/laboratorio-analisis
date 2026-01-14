package com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaMedicaDtoRequest {

    @NotBlank
    @Size(max = 100)
    private String nombreArea;

    @Size(max = 255)
    private String descripcion;
}
