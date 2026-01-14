package com.laboratorio.analisis_clinico.paciente.application.usecase.commands;

import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import com.laboratorio.analisis_clinico.paciente.domain.enume.Sexo;
import com.laboratorio.analisis_clinico.paciente.domain.enume.TipoPaciente;

public class CrearPaciente {

    private final IPacienteRepo pacienteRepo;

    public CrearPaciente(IPacienteRepo pacienteRepo) {
        this.pacienteRepo = pacienteRepo;
    }

    public void ejecutar(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String dni,
            Sexo sexo,
            int edad,
            TipoPaciente tipoPaciente,
            String numeroHistoria,
            String zonaProcedencia,
            String telefono
    ) {

        pacienteRepo.findByDni(dni).ifPresent(p -> {
            throw new IllegalArgumentException("Ya existe un paciente con ese DNI.");
        });

        Paciente paciente = new Paciente(
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                dni,
                sexo,
                edad,
                tipoPaciente,
                numeroHistoria,
                zonaProcedencia,
                telefono
        );

        pacienteRepo.save(paciente);
    }
}

