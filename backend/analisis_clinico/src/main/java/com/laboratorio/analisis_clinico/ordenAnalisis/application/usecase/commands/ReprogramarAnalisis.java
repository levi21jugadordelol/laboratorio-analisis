package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.exception.OrdenAnalisisNotFoundException;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;

import java.time.LocalDateTime;

public class ReprogramarAnalisis {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;

    public ReprogramarAnalisis(IOrdenAnalisisRepo ordenAnalisisRepo) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
    }

    public void ejecutar(Long ordenAnalisisId, LocalDateTime nuevaFecha) {

        OrdenAnalisis ordenAnalisis = ordenAnalisisRepo.findById(ordenAnalisisId)
                .orElseThrow(() -> new OrdenAnalisisNotFoundException("El análisis no existe."));

        ordenAnalisis.reprogramar(nuevaFecha);

        ordenAnalisisRepo.save(ordenAnalisis);
    }
}

