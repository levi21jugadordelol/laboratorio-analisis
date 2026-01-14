package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.List;

public class VerHistorialVersionesFormato {

    private final IFormatoAnalisisRepo formatoRepo;

    public VerHistorialVersionesFormato(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public List<FormatoAnalisis> ejecutar(Long analisisId, String nombreFormato) {
        return formatoRepo.findByAnalisisIdAndNombreFormatoOrderByVersionDesc(
                analisisId, nombreFormato
        );
    }
}

