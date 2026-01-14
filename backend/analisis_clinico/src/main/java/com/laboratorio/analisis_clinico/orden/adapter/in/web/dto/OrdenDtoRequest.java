package com.laboratorio.analisis_clinico.orden.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdenDtoRequest {

    @NotNull
    private Long pacienteId;

    @NotNull
    private TipoOrden tipoOrden;

    /**
     * Obligatorio solo si tipoOrden = RECETA
     * (la regla fuerte la valida el dominio)
     */
    private Long doctorId;

    @NotNull
    private Long createdByUsuario;
}
