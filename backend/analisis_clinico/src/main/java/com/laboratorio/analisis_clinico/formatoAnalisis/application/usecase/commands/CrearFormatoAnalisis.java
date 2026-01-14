package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.Map;

public class CrearFormatoAnalisis {

    private final IFormatoAnalisisRepo formatoRepo;
    private final IAnalisisRepo analisisRepo;

    public CrearFormatoAnalisis(
            IFormatoAnalisisRepo formatoRepo,
            IAnalisisRepo analisisRepo
    ) {
        this.formatoRepo = formatoRepo;
        this.analisisRepo = analisisRepo;
    }

    public void ejecutar(
            Long analisisId,
            String nombreFormato,
            String descripcion,
            Map<String, Object> estructura,
            Long usuarioId
    ) {

        if (!analisisRepo.findById(analisisId).isPresent()) {
            throw new IllegalArgumentException("El análisis no existe.");
        }

        FormatoAnalisis formato = new FormatoAnalisis(
                analisisId,
                nombreFormato,
                descripcion,
                estructura,
                usuarioId
        );

        formatoRepo.save(formato);
    }
}

