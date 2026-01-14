package com.laboratorio.analisis_clinico.reporte.application.usecase.commands;

import com.laboratorio.analisis_clinico.reporte.application.port.out.IReporteRepo;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import com.laboratorio.analisis_clinico.reporte.domain.enume.TipoReporte;

public class GenerarReporteAdHoc {

    private final IReporteRepo reporteRepo;

    public GenerarReporteAdHoc(IReporteRepo reporteRepo) {
        this.reporteRepo = reporteRepo;
    }

    public void ejecutar(
            TipoReporte tipoReporte,
            String periodo,
            String archivoUrl,
            boolean envioAutomaticoCorreo,
            Long usuarioId
    ) {

        Reporte reporte = new Reporte(
                tipoReporte,
                periodo,
                archivoUrl,
                envioAutomaticoCorreo,
                usuarioId
        );

        reporteRepo.save(reporte);
    }
}

