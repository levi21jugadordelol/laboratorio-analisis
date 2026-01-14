package com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands;

import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class RegistrarPersonalMedico {

    private final IPersonalMedicoRepo repo;

    public RegistrarPersonalMedico(IPersonalMedicoRepo repo) {
        this.repo = repo;
    }

    public void ejecutar(
            String nombre,
            String apellido,
            String especialidad
    ) {
        PersonalMedico medico = new PersonalMedico(
                nombre,
                apellido,
                especialidad
        );

        repo.save(medico);
    }
}

