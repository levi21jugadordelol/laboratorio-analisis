package com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrdenAnalisisDtoResponse {

    private Long idOrdenAnalisis;
    private Long ordenId;
    private Long formatoId;
    private EstadoOrdenMedicaAnalisis estado;
    private Prioridad prioridad;
    private LocalDateTime fechaRealizacion;
}
