package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.List;

public class ConsultarFormatosPorVersion {

    private final IFormatoAnalisisRepo formatoRepo;

    public ConsultarFormatosPorVersion(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public List<FormatoAnalisis> ejecutar(Long analisisId, double version) {
        return formatoRepo.findByAnalisisIdAndVersion(analisisId, version);
    }
}

