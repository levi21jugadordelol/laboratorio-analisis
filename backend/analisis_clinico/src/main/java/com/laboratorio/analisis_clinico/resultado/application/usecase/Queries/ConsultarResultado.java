package com.laboratorio.analisis_clinico.resultado.application.usecase.Queries;

import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;

public class ConsultarResultado {

    private final IResultadoRepo resultadoRepo;

    public ConsultarResultado(IResultadoRepo resultadoRepo) {
        this.resultadoRepo = resultadoRepo;
    }

    public Resultado ejecutar(Long resultadoId) {
        return resultadoRepo.findById(resultadoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Resultado no encontrado.")
                );
    }
}

