package com.laboratorio.analisis_clinico.analisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;
import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class CrearAnalisisClinico {

    private final IAnalisisRepo analisisRepo;
    private final IAreaMedicaRepo areaMedicaRepo;

    public CrearAnalisisClinico(
            IAnalisisRepo analisisRepo,
            IAreaMedicaRepo areaMedicaRepo
    ) {
        this.analisisRepo = analisisRepo;
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public void ejecutar(
            String nombreAnalisis,
            String descripcion,
            Long areaMedicaId
    ) {

        AreaMedica areaMedica = areaMedicaRepo.findById(areaMedicaId)
                .orElseThrow(() ->
                        new AnalisisNotFoundException(
                                "El área médica no existe."
                        )
                );

        Analisis analisis = new Analisis(
                nombreAnalisis,
                descripcion,
                areaMedica
        );

        analisisRepo.save(analisis);
    }
}
