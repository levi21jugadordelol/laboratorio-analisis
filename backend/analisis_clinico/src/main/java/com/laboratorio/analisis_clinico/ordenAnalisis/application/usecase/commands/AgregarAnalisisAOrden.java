package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.service.OrdenAnalisisDomainService;

import java.time.LocalDateTime;

public class AgregarAnalisisAOrden {

    private final IOrdenRepo ordenRepo;
    private final IFormatoAnalisisRepo formatoRepo;
    private final IOrdenAnalisisRepo ordenAnalisisRepo;
    private final OrdenAnalisisDomainService domainService;

    public AgregarAnalisisAOrden(
            IOrdenRepo ordenRepo,
            IFormatoAnalisisRepo formatoRepo,
            IOrdenAnalisisRepo ordenAnalisisRepo,
            OrdenAnalisisDomainService domainService
    ) {
        this.ordenRepo = ordenRepo;
        this.formatoRepo = formatoRepo;
        this.ordenAnalisisRepo = ordenAnalisisRepo;
        this.domainService = domainService;
    }

    public void ejecutar(
            Long ordenId,
            Long formatoId,
            Prioridad prioridad,
            LocalDateTime fechaProgramada
    ) {

        Orden orden = ordenRepo.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        FormatoAnalisis formato = formatoRepo.findById(formatoId)
                .orElseThrow(() -> new IllegalArgumentException("Formato no encontrado"));

        OrdenAnalisis nuevoAnalisis = domainService.agregarAnalisisAOrden(
                orden,
                formato,
                prioridad,
                fechaProgramada
        );

        ordenAnalisisRepo.save(nuevoAnalisis);
    }
}

