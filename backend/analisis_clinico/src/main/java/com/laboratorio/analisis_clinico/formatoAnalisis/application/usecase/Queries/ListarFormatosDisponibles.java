package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.List;

public class ListarFormatosDisponibles {

    private final IFormatoAnalisisRepo formatoRepo;

    public ListarFormatosDisponibles(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public List<FormatoAnalisis> ejecutar(Long analisisId) {
        return formatoRepo.findByAnalisisId(analisisId).stream()
                .filter(FormatoAnalisis::estaVigente)
                .toList();
    }
}

