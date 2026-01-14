package com.laboratorio.analisis_clinico.orden.application.usecase.commands;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

public class CancelarOrden {

    private final IOrdenRepo ordenRepo;

    public CancelarOrden(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public void ejecutar(Long ordenId) {

        Orden orden = ordenRepo.findById(ordenId)
                .orElseThrow(() ->
                        new IllegalArgumentException("La orden no existe.")
                );

        orden.anular();

        ordenRepo.save(orden);
    }
}

