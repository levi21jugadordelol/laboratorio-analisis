package com.laboratorio.analisis_clinico.paciente.application.usecase.commands;

import com.laboratorio.analisis_clinico.paciente.application.exception.PacienteNotFoundException;
import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;

public class EditarPaciente {

    private final IPacienteRepo pacienteRepo;

    public EditarPaciente(IPacienteRepo pacienteRepo) {
        this.pacienteRepo = pacienteRepo;
    }

    public void ejecutar(
            Long pacienteId,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            int edad,
            String zonaProcedencia,
            String telefono
    ) {

        Paciente paciente = pacienteRepo.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("El paciente no existe."));

        paciente.actualizarDatos(
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                edad,
                zonaProcedencia,
                telefono
        );

        pacienteRepo.save(paciente);
    }
}

