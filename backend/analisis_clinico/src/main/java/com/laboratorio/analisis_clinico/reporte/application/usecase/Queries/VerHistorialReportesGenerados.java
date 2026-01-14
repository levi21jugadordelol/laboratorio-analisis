package com.laboratorio.analisis_clinico.reporte.application.usecase.Queries;

import com.laboratorio.analisis_clinico.reporte.application.port.out.IReporteRepo;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;

import java.util.List;

public class VerHistorialReportesGenerados {

    private final IReporteRepo reporteRepo;

    public VerHistorialReportesGenerados(IReporteRepo reporteRepo) {
        this.reporteRepo = reporteRepo;
    }

    public List<Reporte> ejecutar() {
        return reporteRepo.findAll();
    }
}

