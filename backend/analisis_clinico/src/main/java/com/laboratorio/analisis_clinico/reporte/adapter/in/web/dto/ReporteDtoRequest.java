package com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.reporte.domain.enume.TipoReporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteDtoRequest {

    @NotNull
    private TipoReporte tipoReporte;

    @NotBlank
    @Size(max = 50)
    private String periodo;

    @NotBlank
    @Size(max = 255)
    private String archivoUrl;

    private boolean envioAutomaticoCorreo;

    @NotNull
    private Long createdByUsuario;
}

