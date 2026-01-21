package com.laboratorio.analisis_clinico.analisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;

public class ConsultarAnalisis {

    private final IAnalisisRepo analisisRepo;

    public ConsultarAnalisis(IAnalisisRepo analisisRepo) {
        this.analisisRepo = analisisRepo;
    }

    public Analisis ejecutar(Long analisisId) {

        return analisisRepo.findById(analisisId)
                .orElseThrow(() ->
                        new AnalisisNotFoundException(
                                "El análisis no existe."
                        )
                );
    }
}

