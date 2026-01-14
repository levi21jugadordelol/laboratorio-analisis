package com.laboratorio.analisis_clinico.resultado.application.usecase.commands;

import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;

public class ConsultarResultadoPorOrdenAnalisis {

    private final IResultadoRepo resultadoRepo;

    public ConsultarResultadoPorOrdenAnalisis(IResultadoRepo resultadoRepo) {
        this.resultadoRepo = resultadoRepo;
    }

    public Resultado ejecutar(Long ordenAnalisisId) {
        return resultadoRepo.findByOrdenAnalisisId(ordenAnalisisId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe resultado para el análisis indicado."
                        )
                );
    }
}

