package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

public class ConsultarFormatoAnalisisPorIdONombre {

    private final IFormatoAnalisisRepo formatoRepo;

    public ConsultarFormatoAnalisisPorIdONombre(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public FormatoAnalisis ejecutarPorId(Long formatoId) {
        return formatoRepo.findById(formatoId)
                .orElseThrow(() -> new IllegalArgumentException("Formato no existe."));
    }

    public FormatoAnalisis ejecutarPorNombre(Long analisisId, String nombreFormato) {
        return formatoRepo.findByAnalisisIdAndNombreFormato(
                        analisisId, nombreFormato
                )
                .orElseThrow(() -> new IllegalArgumentException("Formato no existe."));
    }
}

