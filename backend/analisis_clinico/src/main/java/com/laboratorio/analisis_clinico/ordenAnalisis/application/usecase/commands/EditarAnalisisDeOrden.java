package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;

public class EditarAnalisisDeOrden {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;
    private final IFormatoAnalisisRepo formatoRepo;

    public EditarAnalisisDeOrden(
            IOrdenAnalisisRepo ordenAnalisisRepo,
            IFormatoAnalisisRepo formatoRepo
    ) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
        this.formatoRepo = formatoRepo;
    }

    public void ejecutar(
            Long ordenAnalisisId,
            Long nuevoFormatoId,
            Prioridad nuevaPrioridad
    ) {

        OrdenAnalisis ordenAnalisis = ordenAnalisisRepo.findById(ordenAnalisisId)
                .orElseThrow(() ->
                        new IllegalArgumentException("El análisis no existe.")
                );

        if (nuevoFormatoId != null) {
            FormatoAnalisis nuevoFormato = formatoRepo.findById(nuevoFormatoId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("El formato no existe.")
                    );

            ordenAnalisis.cambiarFormato(nuevoFormato);
        }

        if (nuevaPrioridad != null) {
            ordenAnalisis.cambiarPrioridad(nuevaPrioridad);
        }

        ordenAnalisisRepo.save(ordenAnalisis);
    }
}

