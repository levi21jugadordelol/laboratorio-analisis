package com.laboratorio.analisis_clinico.orden.application.usecase.commands;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;
import com.laboratorio.analisis_clinico.paciente.application.exception.PacienteNotFoundException;
import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import com.laboratorio.analisis_clinico.personalMedico.application.exception.PersonalNotFoundException;
import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class CrearOrden {

    private final IOrdenRepo ordenRepo;
    private final IPacienteRepo pacienteRepo;
    private final IPersonalMedicoRepo personalMedicoRepo;

    public CrearOrden(
            IOrdenRepo ordenRepo,
            IPacienteRepo pacienteRepo,
            IPersonalMedicoRepo personalMedicoRepo
    ) {
        this.ordenRepo = ordenRepo;
        this.pacienteRepo = pacienteRepo;
        this.personalMedicoRepo = personalMedicoRepo;
    }

    public void ejecutar(
            Long pacienteId,
            TipoOrden tipoOrden,
            Long doctorId,
            Long usuarioId
    ) {

        Paciente paciente = pacienteRepo.findById(pacienteId)
                .orElseThrow(() ->
                        new PacienteNotFoundException("El paciente no existe.")
                );

        PersonalMedico medico = personalMedicoRepo.findById(doctorId)
                .orElseThrow(() ->
                        new PersonalNotFoundException("El médico no existe.")
                );

        Orden orden = new Orden(
                tipoOrden,
                usuarioId
        );

        orden.asignarPaciente(paciente);
        orden.asignarPersonal(medico);

        ordenRepo.save(orden);
    }
}

