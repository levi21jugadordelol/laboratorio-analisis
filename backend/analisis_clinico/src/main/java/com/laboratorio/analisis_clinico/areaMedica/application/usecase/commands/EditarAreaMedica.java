package com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands;

import com.laboratorio.analisis_clinico.areaMedica.application.exception.AreaMedicaNotFoundException;
import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class EditarAreaMedica {

    private final IAreaMedicaRepo areaMedicaRepo;

    public EditarAreaMedica(IAreaMedicaRepo areaMedicaRepo) {
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public void ejecutar(
            Long areaMedicaId,
            String nombreArea,
            String descripcion
    ) {

        AreaMedica areaMedica = areaMedicaRepo.findById(areaMedicaId)
                .orElseThrow(() ->
                        new AreaMedicaNotFoundException(
                                "El área médica no existe."
                        )
                );


        areaMedica.actualizarDatos(nombreArea, descripcion);

        areaMedicaRepo.save(areaMedica);
    }
}

