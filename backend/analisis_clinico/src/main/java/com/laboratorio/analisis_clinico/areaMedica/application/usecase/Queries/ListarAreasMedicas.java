package com.laboratorio.analisis_clinico.areaMedica.application.usecase.Queries;

import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

import java.util.List;

public class ListarAreasMedicas {

    private final IAreaMedicaRepo areaMedicaRepo;

    public ListarAreasMedicas(IAreaMedicaRepo areaMedicaRepo) {
        this.areaMedicaRepo = areaMedicaRepo;
    }

    public List<AreaMedica> ejecutar() {
        return areaMedicaRepo.findAll();
    }
}

