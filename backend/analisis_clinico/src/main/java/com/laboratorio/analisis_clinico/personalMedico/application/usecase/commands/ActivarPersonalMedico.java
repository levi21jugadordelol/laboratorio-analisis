package com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands;

import com.laboratorio.analisis_clinico.personalMedico.application.exception.PersonalNotFoundException;
import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class ActivarPersonalMedico {

    private final IPersonalMedicoRepo repo;

    public ActivarPersonalMedico(IPersonalMedicoRepo repo) {
        this.repo = repo;
    }

    public void ejecutar(Long medicoId) {
        PersonalMedico medico = repo.findById(medicoId)
                .orElseThrow(() -> new PersonalNotFoundException("El médico no existe."));

        medico.activar();
        repo.save(medico);
    }
}

