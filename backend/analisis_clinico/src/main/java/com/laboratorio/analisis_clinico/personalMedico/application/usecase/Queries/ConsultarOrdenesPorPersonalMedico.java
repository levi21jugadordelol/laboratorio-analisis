package com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

import java.util.List;

public class ConsultarOrdenesPorPersonalMedico {

    private final IOrdenRepo ordenRepo;

    public ConsultarOrdenesPorPersonalMedico(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public List<Orden> ejecutar(Long personalMedicoId) {
        return ordenRepo.findByDoctorId(personalMedicoId);
    }
}

