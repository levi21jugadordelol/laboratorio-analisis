package com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.paciente.domain.enume.Sexo;
import com.laboratorio.analisis_clinico.paciente.domain.enume.TipoPaciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PacienteDtoResponse {

    private Long idPaciente;
    private String nombrePaciente;
    private String apellidoPaternoPaciente;
    private String apellidoMaternoPaciente;
    private String dni;
    private Sexo sexo;
    private int edad;
    private String zonaProcedencia;
    private TipoPaciente tipoPaciente;
    private String numeroHistorialClinica;
    private LocalDateTime fechaRegistro;
    private String telefono;
}

