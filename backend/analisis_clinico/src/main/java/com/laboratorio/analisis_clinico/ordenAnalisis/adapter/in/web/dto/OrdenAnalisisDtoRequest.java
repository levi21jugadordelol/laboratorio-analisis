package com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdenAnalisisDtoRequest {

    @NotNull
    private Long ordenId;

    @NotNull
    private Long formatoId;

    @NotNull
    private Prioridad prioridad;
}
