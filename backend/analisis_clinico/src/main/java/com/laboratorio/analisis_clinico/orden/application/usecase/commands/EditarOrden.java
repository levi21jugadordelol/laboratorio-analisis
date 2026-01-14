package com.laboratorio.analisis_clinico.orden.application.usecase.commands;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

public class EditarOrden {

    private final IOrdenRepo ordenRepo;

    public EditarOrden(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public void ejecutar(Long ordenId, Long nuevoDoctorId) {

        Orden orden = ordenRepo.findById(ordenId)
                .orElseThrow(() ->
                        new IllegalArgumentException("La orden no existe.")
                );

        orden.cambiarDoctor(nuevoDoctorId);

        ordenRepo.save(orden);
    }
}

