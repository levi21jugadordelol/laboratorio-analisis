package com.laboratorio.analisis_clinico.orden.application.usecase.Queries;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

import java.util.List;

public class ConsultarOrdenesPorPaciente {

    private final IOrdenRepo ordenRepo;

    public ConsultarOrdenesPorPaciente(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public List<Orden> ejecutar(Long pacienteId) {
        return ordenRepo.findByPacienteId(pacienteId);
    }
}

