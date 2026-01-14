package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

public class MarcarFormatoAnalisisComoObsoleto {

    private final IFormatoAnalisisRepo formatoRepo;

    public MarcarFormatoAnalisisComoObsoleto(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public void ejecutar(Long formatoId) {

        FormatoAnalisis formato = formatoRepo.findById(formatoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El formato no existe.")
                );

        formato.marcarComoObsoleto();

        formatoRepo.save(formato);
    }
}

