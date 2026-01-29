package com.laboratorio.analisis_clinico.reporte.application.usecase.Queries;

import com.laboratorio.analisis_clinico.reporte.application.exception.ReporteNotFoundException;
import com.laboratorio.analisis_clinico.reporte.application.port.out.IReporteRepo;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;

public class ConsultarReporte {

    private final IReporteRepo repo;

    public ConsultarReporte(IReporteRepo repo) {
        this.repo = repo;
    }

    public Reporte ejecutar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ReporteNotFoundException("Reporte no encontrado."));
    }
}

