package com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.Map;

public class CrearNuevaVersionFormatoAnalisis {

    private final IFormatoAnalisisRepo formatoRepo;

    public CrearNuevaVersionFormatoAnalisis(IFormatoAnalisisRepo formatoRepo) {
        this.formatoRepo = formatoRepo;
    }

    public void ejecutar(
            Long formatoId,
            Map<String, Object> nuevaEstructura,
            Long usuarioId
    ) {

        FormatoAnalisis actual = formatoRepo.findById(formatoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El formato no existe.")
                );

        FormatoAnalisis nuevaVersion =
                actual.crearNuevaVersion(nuevaEstructura, usuarioId);

        formatoRepo.save(actual);        // queda obsoleto
        formatoRepo.save(nuevaVersion);  // nueva versión
    }
}

