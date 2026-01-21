package com.laboratorio.analisis_clinico.analisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;

public class ActivarDesactivarAnalisis {

    private final IAnalisisRepo analisisRepo;

    public ActivarDesactivarAnalisis(IAnalisisRepo analisisRepo) {
        this.analisisRepo = analisisRepo;
    }

    public void activar(Long analisisId) {

        Analisis analisis = analisisRepo.findById(analisisId)
                .orElseThrow(() ->
                        new AnalisisNotFoundException("El análisis no existe")
                );

        analisis.activar();

        analisisRepo.save(analisis);
    }

    public void desactivar(Long analisisId) {

        Analisis analisis = analisisRepo.findById(analisisId)
                .orElseThrow(() ->
                        new AnalisisNotFoundException("El análisis no existe")
                );

        analisis.inactivar();

        analisisRepo.save(analisis);
    }
}

