package com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalMedicoDtoRequest {

    @NotBlank
    @Size(max = 100)
    private String nombrePersonalMedico;

    @NotBlank
    @Size(max = 100)
    private String apellidoPersonalMedico;

    @NotBlank
    @Size(max = 100)
    private String especialidad;
}

