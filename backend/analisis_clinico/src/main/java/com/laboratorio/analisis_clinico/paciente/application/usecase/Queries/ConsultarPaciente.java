package com.laboratorio.analisis_clinico.paciente.application.usecase.Queries;

import com.laboratorio.analisis_clinico.paciente.application.exception.PacienteNotFoundException;
import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;

public class ConsultarPaciente {

    private final IPacienteRepo pacienteRepo;

    public ConsultarPaciente(IPacienteRepo pacienteRepo) {
        this.pacienteRepo = pacienteRepo;
    }

    public Paciente ejecutar(Long pacienteId) {
        return pacienteRepo.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado."));
    }
}

