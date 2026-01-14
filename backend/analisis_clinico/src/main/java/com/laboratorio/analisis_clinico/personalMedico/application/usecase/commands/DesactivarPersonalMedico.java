package com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands;

import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class DesactivarPersonalMedico {

    private final IPersonalMedicoRepo repo;

    public DesactivarPersonalMedico(IPersonalMedicoRepo repo) {
        this.repo = repo;
    }

    public void ejecutar(Long medicoId) {
        PersonalMedico medico = repo.findById(medicoId)
                .orElseThrow(() -> new IllegalArgumentException("El médico no existe."));

        medico.desactivar();
        repo.save(medico);
    }
}

