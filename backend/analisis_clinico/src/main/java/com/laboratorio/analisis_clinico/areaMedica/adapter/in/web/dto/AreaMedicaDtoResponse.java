package com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.areaMedica.domain.enume.EstadoAreaMedica;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AreaMedicaDtoResponse {
    private Long idArea;
    private String nombreArea;
    private String descripcion;
    private EstadoAreaMedica estadoAreaMedica;

}
