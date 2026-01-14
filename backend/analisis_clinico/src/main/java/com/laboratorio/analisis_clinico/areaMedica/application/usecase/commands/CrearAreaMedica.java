package com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands;

import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class CrearAreaMedica {

    private final IAreaMedicaRepo areaMedicaRepo;

    public CrearAreaMedica(IAreaMedicaRepo areaMedicaRepo) {
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public void ejecutar(String nombreArea, String descripcion) {

        AreaMedica areaMedica = new AreaMedica(
                nombreArea,
                descripcion
        );

        areaMedicaRepo.save(areaMedica);
    }
}

