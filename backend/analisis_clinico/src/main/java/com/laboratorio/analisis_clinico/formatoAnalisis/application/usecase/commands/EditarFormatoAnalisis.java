package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

public class EditarFormatoAnalisis {

    private final IFormatoAnalisisRepo formatoRepo;

    public EditarFormatoAnalisis(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public void ejecutar(
            Long formatoId,
            String nombreFormato,
            String descripcion
    ) {

        FormatoAnalisis formato = formatoRepo.findById(formatoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El formato no existe.")
                );

        formato.actualizarDatos(nombreFormato, descripcion);

        formatoRepo.save(formato);
    }
}

