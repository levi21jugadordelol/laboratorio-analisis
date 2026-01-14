package com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.personalMedico.domain.enume.EstadoPersonalMedico;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonalMedicoDtoResponse {

    private Long idPersonalMedico;
    private String nombrePersonalMedico;
    private String apellidoPersonalMedico;
    private String especialidad;
    private EstadoPersonalMedico estadoPersonalMedico;
}

