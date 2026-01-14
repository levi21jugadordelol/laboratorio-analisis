package com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto;


import com.laboratorio.analisis_clinico.formatoAnalisis.domain.enume.EstadoFormato;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class FormatoAnalisisDtoResponse {

    private Long idFormatoAnalisis;
    private Long analisisId;
    private String nombreFormato;
    private String descripcion;
    private Map<String, Object> estructuraFormato;
    private double version;
    private Long createdByUsuario;
    private LocalDateTime fechaCreacion;
    private EstadoFormato estadoFormato;
}
