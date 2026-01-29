package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.exception.OrdenAnalisisNotFoundException;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;

public class CancelarAnalisis {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;

    public CancelarAnalisis(IOrdenAnalisisRepo ordenAnalisisRepo) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
    }

    public void ejecutar(Long ordenAnalisisId) {

        OrdenAnalisis ordenAnalisis = ordenAnalisisRepo.findById(ordenAnalisisId)
                .orElseThrow(() -> new OrdenAnalisisNotFoundException("El análisis no existe."));

        ordenAnalisis.cancelar();

        ordenAnalisisRepo.save(ordenAnalisis);
    }
}

