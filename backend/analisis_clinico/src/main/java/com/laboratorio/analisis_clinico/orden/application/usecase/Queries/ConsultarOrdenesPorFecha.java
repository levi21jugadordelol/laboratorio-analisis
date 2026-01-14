package com.laboratorio.analisis_clinico.orden.application.usecase.Queries;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultarOrdenesPorFecha {

    private final IOrdenRepo ordenRepo;

    public ConsultarOrdenesPorFecha(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public List<Orden> ejecutar(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    ) {
        return ordenRepo.findByFechaCreacionBetween(
                fechaInicio,
                fechaFin
        );
    }
}

