package com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.reporte.domain.enume.TipoReporte;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReporteDtoResponse {

    private Long idReporte;
    private TipoReporte tipoReporte;
    private String periodo;
    private LocalDateTime fechaGenerada;
    private String archivoUrl;
    private boolean envioAutomaticoCorreo;
    private Long createdByUsuario;
}

