package com.laboratorio.analisis_clinico.orden.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.orden.domain.enume.EstadoOrden;
import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrdenDtoResponse {

    private Long idOrden;
    private Long pacienteId;
    private TipoOrden tipoOrden;
    private Long doctorId;
    private EstadoOrden estadoOrden;
    private LocalDateTime fechaCreacion;
    private Long createdByUsuario;
}

