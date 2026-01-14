package com.laboratorio.analisis_clinico.paciente.application.usecase.Queries;

import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;

import java.util.Optional;

public class BuscarPacientePorDniONombre {

    private final IPacienteRepo pacienteRepo;

    public BuscarPacientePorDniONombre(IPacienteRepo pacienteRepo) {
        this.pacienteRepo = pacienteRepo;
    }

    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteRepo.findByDni(dni);
    }
}

