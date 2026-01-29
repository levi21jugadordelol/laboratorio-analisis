package com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands;

import com.laboratorio.analisis_clinico.personalMedico.application.exception.PersonalNotFoundException;
import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class EditarPersonalMedico {

    private final IPersonalMedicoRepo repo;

    public EditarPersonalMedico(IPersonalMedicoRepo repo) {
        this.repo = repo;
    }

    public void ejecutar(
            Long medicoId,
            String nombre,
            String apellido,
            String especialidad
    ) {
        PersonalMedico medico = repo.findById(medicoId)
                .orElseThrow(() -> new PersonalNotFoundException("El médico no existe."));

        medico.actualizarDatos(nombre, apellido, especialidad);

        repo.save(medico);
    }
}

