package com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands;

import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class ActivarDesactivarAreaMedica {

    private final IAreaMedicaRepo areaMedicaRepo;

    public ActivarDesactivarAreaMedica(IAreaMedicaRepo areaMedicaRepo) {
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public void activar(Long areaMedicaId) {

        AreaMedica areaMedica = areaMedicaRepo.findById(areaMedicaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El área médica no existe."
                        )
                );

        areaMedica.activar();

        areaMedicaRepo.save(areaMedica);
    }

    public void desactivar(Long areaMedicaId) {

        AreaMedica areaMedica = areaMedicaRepo.findById(areaMedicaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El área médica no existe."
                        )
                );

        areaMedica.desactivar();

        areaMedicaRepo.save(areaMedica);
    }
}

