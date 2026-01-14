package com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.analisis.domain.enume.EstadoAnalisis;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnalisisDtoResponse {
    private Long idAnalisis;
    private String nombreAnalisis;
    private String descripcion;
    private Long areaMedicaId;
    private EstadoAnalisis estadoAnalisis;
}
