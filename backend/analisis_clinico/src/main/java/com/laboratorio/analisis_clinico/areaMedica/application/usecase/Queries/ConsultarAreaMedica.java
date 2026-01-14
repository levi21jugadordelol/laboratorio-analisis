package com.laboratorio.analisis_clinico.areaMedica.application.usecase.Queries;

import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class ConsultarAreaMedica {

    private final IAreaMedicaRepo areaMedicaRepo;

    public ConsultarAreaMedica(IAreaMedicaRepo areaMedicaRepo) {
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public AreaMedica ejecutar(Long areaMedicaId) {

        return areaMedicaRepo.findById(areaMedicaId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El área médica no existe."
                        )
                );
    }
}

