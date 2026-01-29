package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.exception.OrdenAnalisisNotFoundException;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.service.OrdenAnalisisDomainService;

public class CambiarPrioridadAnalisis {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;
    private final OrdenAnalisisDomainService domainService;

    public CambiarPrioridadAnalisis(
            IOrdenAnalisisRepo ordenAnalisisRepo,
            OrdenAnalisisDomainService domainService
    ) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
        this.domainService = domainService;
    }

    public void ejecutar(Long ordenAnalisisId, Prioridad prioridad) {

        OrdenAnalisis ordenAnalisis = ordenAnalisisRepo.findById(ordenAnalisisId)
                .orElseThrow(() -> new OrdenAnalisisNotFoundException("Análisis no encontrado"));

        domainService.cambiarPrioridad(ordenAnalisis, prioridad);

        ordenAnalisisRepo.save(ordenAnalisis);
    }
}


