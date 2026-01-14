package com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.paciente.domain.enume.Sexo;
import com.laboratorio.analisis_clinico.paciente.domain.enume.TipoPaciente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDtoRequest {

    @NotBlank
    @Size(max = 100)
    private String nombrePaciente;

    @NotBlank
    @Size(max = 100)
    private String apellidoPaternoPaciente;

    @NotBlank
    @Size(max = 100)
    private String apellidoMaternoPaciente;

    @NotBlank
    @Size(max = 20)
    private String dni;

    @NotNull
    private Sexo sexo;

    @Min(0)
    private int edad;

    @NotNull
    private TipoPaciente tipoPaciente;

    @NotBlank
    @Size(max = 50)
    private String numeroHistorialClinica;

    @Size(max = 150)
    private String zonaProcedencia;

    @Size(max = 20)
    private String telefono;
}

