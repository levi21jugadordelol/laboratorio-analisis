package com.laboratorio.analisis_clinico.analisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;

public class EditarAnalisis {

    private final IAnalisisRepo analisisRepo;

    public EditarAnalisis(IAnalisisRepo analisisRepo) {
        this.analisisRepo = analisisRepo;
    }

    public void ejecutar(
            Long analisisId,
            String nombreAnalisis,
            String descripcion
    ) {

        Analisis analisis = analisisRepo.findById(analisisId)
                .orElseThrow(() ->
                        new AnalisisNotFoundException(
                                "El análisis no existe."
                        )
                );

        analisis.actualizarDatos(nombreAnalisis, descripcion);

        analisisRepo.save(analisis);
    }
}

