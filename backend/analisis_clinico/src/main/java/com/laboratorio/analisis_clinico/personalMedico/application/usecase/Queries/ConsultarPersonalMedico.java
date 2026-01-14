package com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries;

import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class ConsultarPersonalMedico {

    private final IPersonalMedicoRepo repo;

    public ConsultarPersonalMedico(IPersonalMedicoRepo repo) {
        this.repo = repo;
    }

    public PersonalMedico ejecutar(Long medicoId) {
        return repo.findById(medicoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Personal médico no encontrado.")
                );
    }
}

