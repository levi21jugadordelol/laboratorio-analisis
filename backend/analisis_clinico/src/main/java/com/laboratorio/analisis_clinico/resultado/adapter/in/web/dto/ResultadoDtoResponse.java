package com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultadoDtoResponse {

    private Long idResultado;
    private Long ordenAnalisisId;
    private Map<String, Object> resultadoJson;
    private LocalDateTime fechaRegistro;
    private String observacion;
    private Long createdByUsuario;
    private double version;
    private boolean validado;
}

